package es.sidelab.animalshelter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	//userGallery
	
	
	//CONSTRUCTORS
	protected User () {};
	
	//GETTERS AND SETTERS
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDni() {
		return userDni;
	}
	public void setUserDni(String userDni) {
		this.userDni = userDni;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserAdress() {
		return userAdress;
	}
	public void setUserAdress(String userAdress) {
		this.userAdress = userAdress;
	}
	public String getUserHouseSize() {
		return userHouseSize;
	}
	public void setUserHouseSize(String userHouseSize) {
		this.userHouseSize = userHouseSize;
	}
	public String getUserGarden() {
		return userGarden;
	}
	public void setUserGarden(String userGarden) {
		this.userGarden = userGarden;
	}
	public int getUserNumChildren() {
		return userNumChildren;
	}
	public void setUserNumChildren(int userNumChildren) {
		this.userNumChildren = userNumChildren;
	}
	public int getUserNumPeopleInHouse() {
		return userNumPeopleInHouse;
	}
	public void setUserNumPeopleInHouse(int userNumPeopleInHouse) {
		this.userNumPeopleInHouse = userNumPeopleInHouse;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
