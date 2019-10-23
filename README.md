# Backend server
Backend server develop using Spring boot and MySQL. To run the server, you need
  - Java 8 or above 
  - Apache Maven 3.6.2 or above 
  - MySQL 8.0.17 or above 

### Database configuration 
- Create the database call ads_play
- Open  `ads-play-api/src/main/resources/application.properties` and add your username and            - password for MySQL in this line 
            `spring.datasource.username= <username>`
            `spring.datasource.password= <password>`

### Run the application
To run the server run this command 
```sh
$ mvn clean install
$ mvn spring-boot:run
```
now the backend server is running.
