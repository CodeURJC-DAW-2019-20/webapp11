package es.sidelab.animalshelter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUser;

	private String userPhoto;
	private String userName;
	private String userDni;
	private int userAge;
	private String userAdress;
	private String userHouseSize;
	private String userGarden;
	private int userNumChildren;
	private int userNumPeopleInHouse;
	private String userEmail;
	private String userPassword;
	private int userCapacity;
	
	@OneToMany(mappedBy = "userOwner")
	private List<UserGalleryPhoto> userGallery;

	@OneToMany(mappedBy = "adoptionUser")
	private List<Adoption> adoptedAnimals;
	
	@OneToMany(mappedBy = "animalAdopter")
	private List<Animal> userAnimalList;

	// CONSTRUCTORS
	
	public User() {
	}

	public User(String userPhoto, String userName, String userDni, int userAge, String userAdress, String userHouseSize,
			String userGarden, int userNumChildren, int userNumPeopleInHouse, String userEmail, String userPassword) {
		this.userPhoto = userPhoto;
		this.userName = userName;
		this.userDni = userDni;
		this.userAge = userAge;
		this.userAdress = userAdress;
		this.userHouseSize = userHouseSize;
		this.userGarden = userGarden;
		this.userNumChildren = userNumChildren;
		this.userNumPeopleInHouse = userNumPeopleInHouse;
		this.userEmail = userEmail;
		this.userPassword = new BCryptPasswordEncoder().encode(userPassword);
		this.userCapacity = this.calcUserCapacity();
		this.userGallery = new ArrayList<>();
		this.adoptedAnimals = new ArrayList<>();
		this.userAnimalList = new ArrayList<>();
	}
	
	
	// FUNCTIONS 
	
	private int calcUserCapacity() {
		int userNum = 0;
		switch (this.userGarden) {
		case "xs":
			userNum = 0;
			break;
		case "s":
			userNum += 2;
			break;
		case "l":
			userNum += 5;
			break;
		case "xl":
			userNum += 6;
			break;
		default:
			userNum += 3;
			break;
		}
		switch (this.userHouseSize) {
		case "xs":
			userNum = 0;
			break;
		case "s":
			userNum += 1;
			break;
		case "l":
			userNum += 4;
			break;
		case "xl":
			userNum += 6;
			break;
		default:
			userNum += 2;
			break;
		}
		return userNum;

	}
	
	public void addAnimal(Animal newAnimal) {
		this.userAnimalList.add(newAnimal);
	}

	// GETTERS AND SETTERS

	public String getUserPhoto() {
		return userPhoto;
	}

	public List<Adoption> getAdoptedAnimals() {
		return adoptedAnimals;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserDni() {
		return userDni;
	}

	public int getUserAge() {
		return userAge;
	}

	public String getUserAdress() {
		return userAdress;
	}

	public String getUserHouseSize() {
		return userHouseSize;
	}

	public String getUserGarden() {
		return userGarden;
	}

	public int getUserNumChildren() {
		return userNumChildren;
	}

	public int getUserNumPeopleInHouse() {
		return userNumPeopleInHouse;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public int getUserCapacity() {
		return userCapacity;
	}

	public List<UserGalleryPhoto> getUserGallery() {
		return userGallery;
	}

	public void addPhotoToUserGallery(UserGalleryPhoto photo) {
		this.userGallery.add(photo);
	}

}
