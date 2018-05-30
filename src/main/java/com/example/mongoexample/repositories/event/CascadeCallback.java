package com.example.mongoexample.repositories.event;

import com.example.mongoexample.annotations.CascadeSave;
import lombok.Data;
import org.springframework.data.mapping.MappingException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;

@Data
public class CascadeCallback implements ReflectionUtils.FieldCallback {

    private Object source;
    private MongoOperations mongoOperations;

    CascadeCallback(final Object source, final MongoOperations mongoOperations) {
        this.source = source;
        this.setMongoOperations(mongoOperations);
    }

    @Override
    public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
            final Object fieldValue = field.get(getSource());

            if (fieldValue != null) {

                if (fieldValue instanceof List<?>) {
                    for (Object item : (List<?>) fieldValue) {
                        checkNSave(item);
                    }
                } else {
                    checkNSave(fieldValue);
                }
            }
        }

    }

    private void checkNSave(Object fieldValue) {
        FieldCallback callback = new FieldCallback();
        ReflectionUtils.doWithFields(fieldValue.getClass(), callback);

        if (!callback.isIdFound()) {
            throw new MappingException("Oops, something went wrong. Child doesn't have @Id?");
        }

        mongoOperations.save(fieldValue);
    }

}
