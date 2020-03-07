## Prerequisites
  Install docker Desktop o similar (https://docs.docker.com/install/)
# Starting MYSQl Database in a docker
  If we don't have mysql image we have to bring it ( ```docker pull mysql```)
  
  ```docker run --name <nameForOurDatabase> -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=animalshelter -e MYSQL_USER =1234 -e MYSQL_PASSWORD=password -d mysql```
 
  
  With this command we will have mysql database running in a docker
  
 # Configure application properties
 
  - We have to change spring.datasource.url to
  ```xml jdbc:mysql://<nameForOurDatabase>:3306/animalshelter?useSSL=false&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true&serverTimezone=UTC```
  
 # Addition of configuration in Build par of pom.xml and build the proyect as Maven build
  - In the build part of pom.xml , in <plugin> we have to add: 
 ```xml
  <configuration> 
    <finalName>users-mysql</finalName> 
  </configuration
  ```
  -We have to build the proyect as Maven build. If we see the logs we will have the path where is the .jar file to run the application
## RUN THE APPLICATION WITH DOCKER
 We have to open a shell and being in the path of the proyect we have to use:
  ``` docker build . -t users-mysql ```
  
  With this command we will have the image to run the docker of the application. We must have mysql docker running for the next step.
  
  We can check if mysql container is running: ``` docker container ls -l ```
  
  ```
  docker run -p 8443:8443 --name users-mysql --link <nameForOurDatabase> -d users-mysql 
  ```
  
  If we want to see the logs for running the docker we can use ```docker logs <nameofTheContainer>```
  
    


