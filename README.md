# Product Service

This is the backend solution, frontend can be found at: https://github.com/xlucasdemelo/products-service-angular

## Technology Stack:
* Java 8
* Spring boot - WEB, JPA, Security
* PostgreSQL
* Flyway for migration

## Getting Started

### Prerequisites

This application uses maven for dependency management, it is mandatory to install it.

```
Java 8: https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
Maven: https://maven.apache.org/download.cgi
PostgreSQL: https://www.postgresql.org/download/ 

```

### Running with maven

Clone the project

```
git clone https://github.com/xlucasdemelo/products-service
```

to start the application:

```
mvn spring-boot:run
```

The application will run at http://localhost:8080

### Running tests

To run both unit and integration Tests, execute the following command:

```
mvn test
```


Integration tests are under the package **com.asellion.productsservice.integration** and can be executed using JUnit runner.
Unit tests are under the package **com.asellion.productsservice.service** and can be executed using JUnit runner.

### Running With Docker

1.Start a postgres container:

```
docker run -p 5432:5432 -d --name docker-postgres -e POSTGRES_DB=db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres  postgres
```

2.Build a app jar

```
mvn package -DskipTests
```

3.Build Docker image

```
docker build -t asellion/products-service .
```

4.Run Docker image changing the DB env variables

```
 docker run -d -it --name products-service --link docker-postgres -e DB_HOST=<YOUR_LOCAL_IP> -e DB_USER=postgres -e DB_PASSWORD=postgres -p 8080:8080 asellion/products-service
```

The application will run at http://localhost:8080

### AWS running application

The application its deployed in AWS using EBS.

You can send the sample requests listed below to: http://asellionproductsservice-env-1.eba-n74xmghk.us-east-2.elasticbeanstalk.com:8080


### Sample requests

The application have the following endpoints:

```
POST: http://localhost:8080/authenticate
GET: http://localhost:8080/api/products
GET: http://localhost:8080/api/products/${productId}
PUT: http://localhost:8080/api/products/${productId}
POST: http://localhost:8080/api/products
```

1.Retrieve a JWT token:

* Post request send a json with username and password
* This token needs to be sent to all the other requests in the header

```
curl http://localhost:8080/authenticate --header "Content-Type: application/json" --request POST --data '{"username":"admin", "password":"password"}'
```

	Sample response: {"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU4MzYyNzY3MCwiaWF0IjoxNTgzNjA5NjcwfQ.WdayMZ5D3bBUs4Dt38J9R2YNJVfpQlcNv2G36Iif6g80_lhBNTo42yxWKTyqO7KJ71iJeyJ8CKMfTKvIICt9aw"}


2.Inserting a Product:

* Post request - send a json with product data
* In the header use the token retrieved at step #1 with "Bearer" before


```
curl http://localhost:8080/api/products --header "Content-Type: application/json" --header "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU4MzYyNzY3MCwiaWF0IjoxNTgzNjA5NjcwfQ.WdayMZ5D3bBUs4Dt38J9R2YNJVfpQlcNv2G36Iif6g80_lhBNTo42yxWKTyqO7KJ71iJeyJ8CKMfTKvIICt9aw" --request POST --data '{"name":"Samsung S10", "currentPrice":"300", "description":"This is a cellphone"}'
```
	Sample response: {"id":2,"name":"Samsung S10","currentPrice":300,"lastUpdated":"2020-03-07T19:46:10.751+0000","description":"This is a cellphone"}
	
3.Updating a Product:

* Post request - send a json with product data
* In the header use the token retrieved at step #1 with "Bearer" before

```
curl http://localhost:8080/api/products/2 --header "Content-Type: application/json" --header "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU4MzYyNzY3MCwiaWF0IjoxNTgzNjA5NjcwfQ.WdayMZ5D3bBUs4Dt38J9R2YNJVfpQlcNv2G36Iif6g80_lhBNTo42yxWKTyqO7KJ71iJeyJ8CKMfTKvIICt9aw" --request PUT --data '{"name":"Samsung S10", "currentPrice":"500", "description":"This is not a cellphone"}'
{"id":2,"name":"Samsung S10","currentPrice":500,"lastUpdated":"2020-03-07T19:46:10.751+0000","description":"This is not a cellphone"}
```
	Sample response: {"id":2,"name":"Samsung S10","currentPrice":500,"lastUpdated":"2020-03-07T19:46:10.751+0000","description":"This is not a cellphone"}

3.List All Products:

* In the header use the token retrieved at step #1 with "Bearer" before

```
curl http://localhost:8080/api/products/ --header "Content-Type: application/json" --header "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU4MzYyNzY3MCwiaWF0IjoxNTgzNjA5NjcwfQ.WdayMZ5D3bBUs4Dt38J9R2YNJVfpQlcNv2G36Iif6g80_lhBNTo42yxWKTyqO7KJ71iJeyJ8CKMfTKvIICt9aw" --request GET
```
It will return a list with all the products

4.Find product by id
* In the header use the token retrieved at step #1 with "Bearer" before

```
curl http://localhost:8080/api/products/2 --header "Content-Type: application/json" --header "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU4MzYyNzY3MCwiaWF0IjoxNTgzNjA5NjcwfQ.WdayMZ5D3bBUs4Dt38J9R2YNJVfpQlcNv2G36Iif6g80_lhBNTo42yxWKTyqO7KJ71iJeyJ8CKMfTKvIICt9aw" --request GET
```

### Logs

I've implemented a custom log4j2 log appender to log the id of the DIFF for each operation, so we can keep track of the operations performed for each Diff object.


All the requests sent to the application will have a unique transaction ID, 
We can use this to integrate with a machine data application like Splunk and investigate operations for each transaction.


```
2020-03-07 16:52:15.399 {transactionId=2330274e-225f-41b0-b487-58a488b84cfd}  INFO lucsa-VPCEH3QFX --- [nio-8080-exec-4] c.a.p.s.ProductServiceImpl               : Retrieving all products from database

```
### DB migration

I am using Flyway for DB migration, the sql files are a **src/main/resources/migration**

### Documentation

I am using Swagger2 to provide the documentation for the application, it could be accessed at: 

```
http://localhost:8080/swagger-ui.html#/
```
