# USER SERVICE API

## Prerequisites

In order to build the project, you will have to install the following:

* [Java 8] (http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* This project includes the [Maven Wrapper] (https://github.com/takari/maven-wrapper) which will automatically downloaded and use the correct version of Maven.
* This project includes Lombok Annotations, this means that in order for your IDE to correctly compile your project you'll need to add the Lombok plugin to your IDE.
    * IntelliJ: https://projectlombok.org/setup/intellij
    * NetBeans: https://projectlombok.org/setup/netbeans
    * Eclipse: https://projectlombok.org/setup/eclipse
* For annotation processing to work for lombok annotations, you'll need to enable annotation processing at a project level in your IDE.
    1. File -> Other Settings -> Default Settings
    2. Build, Execution, Deployment -> Compiler -> Annotation Processors
    3. Enable annotation processing.
    4. Clean, build, invalidate cache and restart after that.

## Build

### Maven

```
./mvnw clean install
```

### Docker

```
docker build -t user-service .
```

## Run

### Maven

```
./mvnw spring-boot:run
```

### Docker

```
docker run -e ENVIRONMENT=${ENVIRONMENT} -e PORT=${PORT} -p ${PORT}:${PORT} -v `pwd`/logs/:/var/app/user-service/logs user-service
Example: docker run -e ENVIRONMENT=dev -e PORT=8080 -p 8080:8080 -v `pwd`/logs/:/var/app/user-service/logs user-service
```

The supported values for ${ENVIRONMENT} are **dev** and **prod**. These are mapped to Spring profiles, which will load 
different beans specific for each environment. Currently **dev** profile mirrors the default profile, so for local 
testing there is no need to explicitly set the profile.

## Configuration

Configuration file 'application.yml' is present in config directory together with profile specific YML files. 
The application.yml is used as a parent and the profile specific ones are used for overrides.

## Code coverage

To run checkstyle, execute: 
Windows: `mvnw.cmd checkstyle:check`
Linux/Mac: `./mvnw checkstyle:check`


To run findbugs, execute: 
Windows: `mvnw.cmd findbugs:findbugs`
Linux/Mac: `./mvnw findbugs:findbugs`

## Testing
To run the unit and integration tests, execute:

```
./mvnw verify
```

To run the unit tests only, execute:

```
./mvnw verify -DskipITs
```

To run the integration tests only, execute:

```
./mvnw verify -DskipUTs
```

## Documentation
Once you run the application, the documentation of the API can be found at: http://localhost:8080/swagger-ui.html



