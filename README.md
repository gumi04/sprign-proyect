# Stub - Products-API


Since during a learnign phase of a new Front-end framework such as : Angular, React, Vue, etc. nowadays it is almost mandatory interact with Rest services, this project was created to interact with a group of Stub REST services that can be deployed without more dependencies than this single component as a fat jar created with springboot. Having said that, this component can be used during a training and avoid the need of a backend services, a deployment platform or a database. 

## Dependencies 

If this project is used within an IDE (i.n. Eclipse, STS, Intellij, etc) it is necessary to install lombok before to import the project. [Lombok page](https://projectlombok.org/).

![lombok](https://projectlombok.org/img/lombok-installer.png)


## Compilation

It needs maven to compile the project

  > mvn clean package 

## Running It

Start it up with:

  > $ mvn spring-boot:run
   
Then go to <http://localhost:8080/>


## Rest API documentation

With the application up and running go to: <http://localhost:8080/api/swagger-ui.html>

![swagger](https://github.com/capgemini-salvgonz/stub-products-api/blob/development/swagger-ui.png)

## In memory console

With the application up and running go to: <http://localhost:8080/h2-console>

![h2-console](https://github.com/capgemini-salvgonz/stub-products-api/blob/development/H2-console.png)


