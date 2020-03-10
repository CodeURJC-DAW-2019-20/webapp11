package es.sidelab.animalshelter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

@Entity
public class WebUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idUser;

    private String userphoto;
	private String userName;
	private String userDni;
	private int userAge;
	private String userAdress;
	private String userHouseSize;
	private String userGarden;
	private int userNumChildren;
	private int userNumPeopleInHouse;
	private String userEmail;
	private int userCapacity;
	@JsonIgnore
	private String userPassword;

	// CONSTRUCTORS

	public WebUser() {

	}

	public WebUser(String userName, String userDni, int userAge, String userAdress, String userHouseSize,
			String userGarden, int userNumChildren, int userNumPeopleInHouse, String userEmail, String userPassword) {
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
	}

	// FUNCTIONS

	private int calcUserCapacity() {
		int userNum = 0;
		switch (this.userGarden) {
		case "no":
			userNum += 0;
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
			userNum += 0;
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

	// GETTERS AND SETTERS

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
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

	public String getUserphoto() {
		return userphoto;
	}

	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}
	
	public void encryptPassword() {
		userPassword = new BCryptPasswordEncoder().encode(userPassword);
	}
}
