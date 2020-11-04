# Product-Reviews
SpringBoot application for products and reviews.

## Architecture
- ```Java 8```
- ```Maven (wrapper)```
- ```core``` : responsible for saving product and review data.
- ```rest``` : responsible for CRUD product and review services.
- ```MariaDB``` as database
- ```Swagger``` documentation available at **/swagger-ui.html**
- ```JUnit``` for unit testing

## Requirements
 - Java 8 (to run locally in IDE)
 - MariaDB
 - Install Lombok plugin
 - Unix based OS (Mac OS, Ubuntu, etc)

## Setup

Navigate to the core and rest modules and change the datasource configuration on the application.yml file.
   
## Startup
Navigate to the core module and run the following commands:  

```$ mvn spring-boot:run```

Navigate to the rest module and run the following commands:  

```$ mvn spring-boot:run```


## API Testing
Postman API requests/tests are available [in this folder](https://github.com/mahdieha/product-reviews/tree/master/API%20Postman%20Tests)

## Swagger Docs
Swagger documentation available:

 - rest : http://localhost:9099/api/v1/swagger-ui.html
