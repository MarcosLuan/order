# Order Manager Project

Order Manager scenario:
This is an API where users can create and manage orders. Items can be ordered and orders are automatically fulfilled as soon as the item stock allows it.

## 🚀 Starting

Spring Boot helps you to create stand-alone,
production-grade Spring-based applications that you can run.
We take an opinionated view of the Spring platform and
third-party libraries, so that you can get started with 
minimum fuss. Most Spring Boot applications need very little
Spring configuration.

You can use Spring Boot to create Java applications that
can be started by using java -jar or more traditional war
deployments. We also provide a command line tool that runs “spring scripts”.

### 📋 Requirements

Spring Boot 2.7.1 requires Java 8 and is compatible up to and including Java 18.
Spring Framework 5.3.21 or above is also required.
Explicit build support is provided for the following build tools:

Build Tool	Version
Maven 3.5+
Gradle 6.8.x, 6.9.x, and 7.x

### 📋 Servlet Containers
Spring Boot supports the following embedded servlet containers:

Tomcat  9.0 - Version 4.0
Jetty 9.4 - Version 3.1
Jetty 10.0 - Version 4.0
Undertow 2.0 - Version 4.0

You can also deploy Spring Boot applications to any servlet 3.1+ compatible container.

### Database
Create a local database named ordermanager. The tables will be created automatically

### 🔧 Configurations

To run this project on your machine, have the necessary java packages installed (JAVA 8 or plus),

After cloning the project, open it in your favorite development environment, start the project and wait for the dependencies to be installed.
All dependencies in pom.xml are necessary for correct operation.

## 🔩 Swagger
Access the path with the project running:
http://localhost:8080/swagger-ui.html

## 🔩 Requisitions Postman

### Bookroom

GET all bookrooms
```
http://localhost:8082/bookroom
```

GET bookrooms by user document
```
http://localhost:8082/bookroom/byDocument/{user_document}
```

POST bookrooms save
```
http://localhost:8082/bookroom/save
{
	"userDocument":"String",
	"userName":"String",
	"phoneUser":"String",
	"entryDate":"2022-07-20",
	"departureDate":"2022-07-22"
}
```

PUT change bookroom
```
http://localhost:8082/bookroom/change
{
    "id":1
	"userDocument":"String",
	"userName":"String",
	"phoneUser":"String",
	"entryDate":"2022-07-20",
	"departureDate":"2022-07-22"
}
```

## 🛠️ built with

* [Java](https://docs.oracle.com/en/java/javase/11/docs/api/) - Programming Language
* [SpringBoot](https://docs.spring.io/spring-boot/docs/current/reference/html/) - Framework Java
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html) - Documentation
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/) - Guide
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/#build-image) - Documentation
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web) - Documentation
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

## ✒️ Author

* **Marcos Luan Kades** - *Complete Project* - [github](https://github.com/MarcosLuan)

## 🎁  Thanks

* Thank you to everyone interested in the project 🤓.

---