# ParkingSystem
This application is to calculate the parking time based on different rules and the input of money transactions. The parking system application is used to facilitate the process of payment in CASH or with a CARD.
# How to install the project
- First step: Is to clone the repository : run in Git Bash git clone https://github.com/sildiRicku/ParkingSystem.git 
(the link is found in the green {<> Code} button)
- Second step: Install dependencies
  - Spring Boot Starter
  - Spring Web Starter
  - Spring Data JPA
- Third step: Run the application
# Deploying into Amazon Web Services by following the steps:
- First step : Create a database in your remote server connect local database with the remote server by changing into the configuration the spring.datasource.url with the remote server aws database link.
- Second step: By generating this command
  - mvn clean install it will build .jar file.
- Third step: .jar file that is generated will be used to deploy the application in an environment with Elastic Beanstalk.
# Application Properties
The database is connected to the local host with respective username and password:
 - spring.datasource.url=jdbc:mysql://localhost:3306/parkingdb
 - spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

To add Spring Profile(dev / test) run this commands:
 - mvn install -D spring.profiles.active=dev
 - mvn install -D spring.profiles.active=test
# Technologies
 - Java 17 Oracle OpenJDK version 17.0.6
 - Framework Spring Boot 3.0.4
 - MySQL Database Oracle
