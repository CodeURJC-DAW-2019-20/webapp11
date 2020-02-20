package es.sidelab.animalshelter;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

@Scope(value= WebApplicationContext.SCOPE_SESSION, 
proxyMode = ScopedProxyMode.TARGET_CLASS)


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
	//private List<String> userGallery;
	
	
	//CONSTRUCTORS
	protected User () {}
	
	public User(String userPhoto, String userName, String userDni, int userAge, String userAdress,
			String userHouseSize, String userGarden, int userNumChildren, int userNumPeopleInHouse, String userEmail,
			String userPassword) {
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
		this.userCapacity = this.calcSizeHouse();
	}



	

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
	public int getUserCapacity() {
		return userCapacity;
	}
	public int calcSizeHouse() {
		int userNum =0;
		switch (this.userGarden) {
			case "xs": userNum=0; break;
			case "s": userNum+=2; break;
			case "m": userNum+=3; break;
			case "l": userNum+=5; break;
			case "xl": userNum+=6; break;
		}
		switch (this.userHouseSize) {
			case "xs": userNum=0; break;
			case "s": userNum+=1; break;
			case "m": userNum+=2; break;
			case "l": userNum+=4; break;
			case "xl": userNum+=6; break;
		}
		return userNum;
		
		
	}
	/*public List<String> getUserGallery() {
		return userGallery;
	}

	public void setUserGallery(List<String> userGallery) {
		this.userGallery = userGallery;
	}*/
	
}
