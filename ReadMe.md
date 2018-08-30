# Scalable Web
This application has developed for <strong>WAES software developer</strong> assigment
	
###Assignment

* Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints
   
    ```<host>/v1/diff/<ID>/left``` and ```<host>/v1/diff/<ID>/right```
     
* The provided data needs to be diff-ed and the results shall be available on a third end
point.

    ```<host>/v1/diff/<ID>```
    
* The results shall provide the following info in JSON format
    * If equal return that
    * If not of equal size just return that
    * If of same size provide insight in where the diffs are, actual diffs are not needed.
        * So mainly offsets + length in the data
* Make assumptions in the implementation explicit, choices are good but need to be
communicated assumptions in the implementation explicit, choices are good but need to be communicated

## Technologies

* Spring Boot - Framework
* H2 - Database
* Tomcat - Server
* Maven - Dependency Management

## Install and Run

First run command below in terminal

```
mvn clean install
```

Then run jar file with command below in terminal

```
java -jar target/scalableweb-0.0.1-SNAPSHOT.jar
```

## Test
Test can be run separately with commands below
```
mvn test
mvn verify
```

 ## Suggestions for improvement
 * <strong>Swagger</strong> is added but not implemented due to lack of time. It is very suitable for <strong>versioning</strong> and documentation if we consider <strong>prefix</strong> of the urls which start with <strong>V1</strong> 
 * <strong>Dockerizing</strong> would be nice, it allows us to test our application across to multiple instances.
 * <strong>Distributed Cache(Redis)</strong> would must have if we run application across to multiple instances. 
 * Authentication would be nice too. 
