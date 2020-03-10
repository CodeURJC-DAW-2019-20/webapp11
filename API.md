## Prerequisites
  Install docker Desktop o similar (https://docs.docker.com/install/)
# Starting MYSQl Database in a docker
  If we don't have mysql image we have to bring it ( ```docker pull mysql```)
  
  ```docker run --name <nameForOurDatabase> -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=animalshelter -e MYSQL_USER =1234 -e MYSQL_PASSWORD=password -d mysql```
 
  
  With this command we will have mysql database running in a docker
  
 # Configure application properties
 
  - We have to change spring.datasource.url to
  ``` jdbc:mysql://<nameForOurDatabase>:3306/animalshelter?useSSL=false&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true&serverTimezone=UTC```
  
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
  
    
# API REST Documentation

## About the API
By using this API Rest you will be able to obtain information about users, animals and adoptions, but also to create, edit and delete them.

##### To use the API you need to download [Postman](https://www.getpostman.com/).

## API requests
You can send GET, POST, PUT and DELETE requests.

### Resources
The resource API has GET, POST, PUT and DELETE methods.
https: //localhost:8443 followed by the containt request URL.

**All API queries have been preceded by /api**

## Authentication

#### WebUser/Shelter login
Allows a web user or a shelter to log in.

* ##### URL:

	< /login >

* ##### Method:

	`GET`

* ##### Success Response for WebUser:
  
  	```
	{
      "idUser": 2,
      "userphoto": null,
      "userName": "Mary",
      "userDni": "no",
      "userAge": 22,
      "userAdress": "C/Lambrusco,10",
      "userHouseSize": "s",
      "userGarden": "no",
      "userNumChildren": 1,
      "userNumPeopleInHouse": 3,
      "userEmail": "iho.ladamadeltiempo@gmail.com",
      "userPassword": "$2a$10$4QdTNf3Z67bmTUv5ArGdOOnwWp/YXG4SSeUBA3LvYR9Nut9JksdRu",
      "userCapacity": 1
  }
	```

* ##### Success Response for Shelter:
  
  	```
	{
      "idShelter": 3,
      "shelterName": "Build Animal Future",
      "shelterNif": "1123123123",
      "shelterEmail": "marinafs.13@hotmail.com",
      "shelterPassword": "$2a$10$inqMbf7HS85fbHv9gfjQcOWAcVJbMg3gnvsCseUFSGcjnWWFn9wki",
      "shelterAverageRating": 0,
      "numVotes": 0,
      "shelterDescription": "NO",
      "shelterAdress": "C/Montana,1"
  }
	```
  
* ##### Error response:

	**Code**: 401 UNAUTHORIZED
	
#### loguot  
Allows a user to log out.

* ##### URL:

	< /logout >

* ##### Method:

	`GET`

* ##### Success Response:

	```
	true
	```

  * ##### Error Response:

	**Code**: 404 NOT FOUND

## Shelter
The following queries will be preceded by /shelters. 
  
### Obtain shelter data
Resource to show all shelters with their data.

* ##### URL

	< / >

* ##### Method:

	`GET`
  
* ##### Success Response:

  	```
	[
    {
        "idShelter": 3,
        "shelterName": "Build Animal Future",
        "shelterNif": "1123123123",
        "shelterEmail": "marinafs.13@hotmail.com",
        "shelterPassword": "$2a$10$inqMbf7HS85fbHv9gfjQcOWAcVJbMg3gnvsCseUFSGcjnWWFn9wki",
        "shelterAverageRating": 0,
        "numVotes": 0,
        "shelterDescription": "NO",
        "shelterAdress": "C/Montana,1"
    },
    {
        "idShelter": 4,
        "shelterName": "Animal Rescue",
        "shelterNif": "4325151451",
        "shelterEmail": "iho-ladamadeltiempo@live.com",
        "shelterPassword": "$2a$10$CGBmEJAlkaSd0QEdbV1eKOLstYIk.hiQdQZCYjuqw3CKMQtUNaKyS",
        "shelterAverageRating": 0,
        "numVotes": 0,
        "shelterDescription": "NO",
        "shelterAdress": "C/Signium,7"
    }
  ]
	
	```
  
* ##### Error Response:

	**Code**: 401 UNAUTHORIZED
  
### Create shelter
Resource to create a new shelter.

* ##### URL

	< / >

* ##### Method:

	`POST`
  
* ##### URL Params:

	`Empty`

* ##### Example of query:

	* URL
		
		`/api/shelters/`

  * ##### Data params:
  
  ```
	{
      "shelterName": "Animalia",
      "shelterNif": "1234567A",
      "shelterEmail": "cadena.2592@gmail.com",
      "shelterPassword": "123",
      "shelterDescription": "NO",
      "shelterAdress": "C/Montana,1"
    }
	
	```

* ##### Success Response:

  	```
	{
      "idShelter": 12,
      "shelterName": "Animalia",
      "shelterNif": "1234567A",
      "shelterEmail": "cadena.2592@gmail.com",
      "shelterPassword": "$2a$10$n6T3vDKn056X8mNGhtS4ReG26RnNZBkpYxbsnetoPnFyS2UAsUR5C",
      "shelterAverageRating": 0,
      "numVotes": 0,
      "shelterDescription": "NO",
      "shelterAdress": "C/Montana,1"
  }
	
	```
  
* ##### Error Response:

	**Code**: 500Internal Server Error
  
## Web User
The following queries will be preceded by /users. 

### Obtain web user data
Resource to show all web users with their data.

* ##### URL

	< / >

* ##### Method:

	`GET`
  
* ##### Success Response:

  	```
	[
    {
        "idUser": 1,
        "userphoto": null,
        "userName": "Peter",
        "userDni": "48755465Q",
        "userAge": 22,
        "userAdress": "C/Manuela Malasaña,15",
        "userHouseSize": "l",
        "userGarden": "m",
        "userNumChildren": 2,
        "userNumPeopleInHouse": 5,
        "userEmail": "correo.usuario@outlook.com",
        "userPassword": "$2a$10$ZACC9YQ6XxHfZ3nyZBefwe4FCtStGtKAV78DEL1usCIpzYqV3VghK",
        "userCapacity": 7
    },
    {
        "idUser": 2,
        "userphoto": null,
        "userName": "Mary",
        "userDni": "no",
        "userAge": 22,
        "userAdress": "C/Lambrusco,10",
        "userHouseSize": "s",
        "userGarden": "no",
        "userNumChildren": 1,
        "userNumPeopleInHouse": 3,
        "userEmail": "iho.ladamadeltiempo@gmail.com",
        "userPassword": "$2a$10$4QdTNf3Z67bmTUv5ArGdOOnwWp/YXG4SSeUBA3LvYR9Nut9JksdRu",
        "userCapacity": 1
    }
  ]
	
	```
  
* ##### Error Response:

	**Code**: 401 UNAUTHORIZED
  
### Create user
Resource to create a new web user.

* ##### URL

	< / >

* ##### Method:

	`POST`
  
* ##### URL Params:

	`Empty`

* ##### Example of query:

	* URL
		
		`/api/users/`

  * ##### Data params:

  	```
	{
      "shelterName": "Animalia",
      "shelterNif": "1234567A",
      "shelterEmail": "cadena.2592@gmail.com",
      "shelterPassword": "123",
      "shelterDescription": "NO",
      "shelterAdress": "C/Montana,1"
    }
	
	```

* ##### Success Response:

  	```
	{
    "idShelter": 12,
    "shelterName": "Animalia",
    "shelterNif": "1234567A",
    "shelterEmail": "cadena.2592@gmail.com",
    "shelterPassword": "$2a$10$n6T3vDKn056X8mNGhtS4ReG26RnNZBkpYxbsnetoPnFyS2UAsUR5C",
    "shelterAverageRating": 0,
    "numVotes": 0,
    "shelterDescription": "NO",
    "shelterAdress": "C/Montana,1"
  }
	
	```
  
* ##### Error Response:

	**Code**: 500Internal Server Error
