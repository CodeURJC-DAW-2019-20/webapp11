 # PETSHELTER 
It’s a web application on animal adoption. Animal shelters and dog pounds can upload their animals data for users to adopt them, and in conclussion for letting users see elegible pets for adoption.

## Members of group

| NAME  | UNIVERSITY MAIL | GITHUB ACCOUNT |
|  ------------- |------------- | ------------- |
| Arshia Ambar  Saleem    | aa.saleem.2017@alumnos.urjc.es  | ArshiaSaleem98  |
| Borja  Castro Cruces  | b.castro.2018@alumnos.urjc.es  | borja123456  |
| Marina Fernández Suárez   | m.fernandezsu@alumnos.urjc.es  | IhoFenixMFS  |
| Rodrigo  Cadena Rodríguez  | r.cadenar.2019@alumnos.urjc.es  | CadenaR  |

## Used Tools 
#### Trello
[Link to our board.](https://trello.com/b/ZzIXGZ6A/aplicaciones-web)


## Entities:
### User:
>Registered users can request the adoption of any animal that the search shows them. In addition, they can upload images to their profiles.
* Attributes:
  * Photo.
  * Name.
  * DNI.
  * Adress.
  * House size (Big/Medium/Little).
  * Garden (No/Little/Medium/Big).
  * Children number.
  * People living in the house.
  * E-mail.
  * Password.
  * Galery.

### Animals:
>The available animals list would be visible, here will appear an image of the animal, his/her name, the age, the owner and if it has been already adopted or not.
* Attributes:
  * Photo.
  * Name.
  * Age.
  * Animal type (Dog/Cat/Bird/Reptile/Equine).
  * Size (XL/Big/Medium/Little).
  * Description.
  * Owner.
  * Status (Adopted or not).


### Shelter:
>The shelter can upload animals for them to get adopted, and accept or deny user adoption requests.
* Attributes:
  * Name.
  * NIF.
  * E-mail.
  * Password.
  * Average Rating.
  * Description.
  * Address.
  * Animal list.
  * Adoption Requests Received (Animal/User).

### Adoptions:
>In order to have a register of the adoptions made by the users an adption table is needed.
* Attributes:
  * Date.
  * Animal ID.
  * User ID.
  
## Users permissions:
### *Anonimous*
Anonymously or through any user type it is possible to access *Home*, the animal list *Animals* and the animal profile, the shelter or users profile, and *Contact Us*.
### *User*
This user type can request adoptions, edit his/her profile data, and upload images to his/her gallery.
### *Shelter*
This user type can edit its profile data, accept or deny its adoption requests, and upload new animals to the platform.

## Images:
Registered users and animals have their profile image which can be edited and updated by them or their shelter in case that is an animal profile. In addition, users have an image gallery associated in which they can upload all the pictures they want.

## Graphs:
The adoption statistics are going to be shown through a diagram in the graph view.

## Complementary Technology:
When a user requests an adoption, an e-mail will be sent to the proprietary shelter.

## Advanced Query or Algorithm:
The shown animal list will filter available animals and change depending on the house size of the user navigating through the web. For example, a big dog wouldn’t appear in the list of a small house owner.

## Screenshots

<img src="ReadmeImgs/index.jpg">

###### This is the initial page, wher all users are going to be address when opening our web App, it has a header and footer that is shared with all the other pages.

<img src="ReadmeImgs/animals.jpg">

###### This page is going to show all the images of the animals that have been registered over time.

<img src="ReadmeImgs/animalview.jpg">

###### In this page the user is going to see the profile of the animal that he wants to adopt.

<img src="ReadmeImgs/profile.jpg">

###### This page is going to show user's profile.

<img src="ReadmeImgs/login.jpg">

###### This is where users can log in.

<img src="ReadmeImgs/error.jpg">

###### This page is to be shown when an error occurs.

<img src="ReadmeImgs/animalform.jpg">

###### This page is for registering a new animal to adopt.

<img src="ReadmeImgs/request.jpg">

###### This image shows how the request is going to look.

<img src="ReadmeImgs/userform.jpg">

###### This page is to register a new user.

<img src="ReadmeImgs/shelterform.jpg">

###### This page is to register a new shelter.

<img src="ReadmeImgs/contact.jpg">

###### This is the page is for the users of the Web App to get in contact with us.

<img src="ReadmeImgs/graph.jpg">

###### This page is going to show the graph with adoption statistics.

## Navigation Diagram

<img src="ReadmeImgs/diagram.jpg">

###### This diagram shows how the navigation in our aplication is intended to be.
<img src= "ReadmeImgs/Untitled Diagram (3).png">

###### This diagram shows the differents templates, controller and services used in the web server

## Entity Diagram

<img src="ReadmeImgs/EntityDataBaseDiagram.PNG">

###### This diagram shows entities with thier fields and relationship of them with Database.
## PARTICIPATION

### Borja Castro Cruces
- Custom Error Page and CustomError Controller
- Algorithm for search by userSizeHouse and userGarden and match with the best animal for him
- ModelAttributeController
- Class and Template Diagrams

### Arshia Ambar Saleem
- Add Animals to adopt
- Show Animal into animal page
- Pagination
- Graph
- Entity Diagram
- Filter Animal (search by type, search by name)

### Marina Fernandez
- Adoption finished implementation
- Animal Carusel implemented and animal profile revision
- User Profile finished with gallery implemented
- Mailing implementation
- Entity relations implemented

### Rodrigo Cadena
- User creation
- CSRF implementation
- Security BCrypt configuration
- https on port 8443
- Session scope component to save logged user/shelter
