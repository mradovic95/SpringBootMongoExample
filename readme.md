#Spring boot mongo example

## Use mongo with spring boot

###Basic

To provide basic functionality(CRUD,pagging,sorting,filtering,...) extend MongoRepository.

###Custom repository



###Cascade save

To provide cascade save functionality we need:

* Extend AbstractMongoEventListener and add cascade save logic in extended class
* Create cascade save annotation(for selecting field we want to save when save parrent object)
* Define been in configuration

## Running service through docker contrainers

```
mvn clean install -DskipTests
mvn dockerfile:build

docker-compose up
```