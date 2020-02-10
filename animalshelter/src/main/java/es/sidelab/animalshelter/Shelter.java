package es.sidelab.animalshelter;

import java.util.ArrayList;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;

@Entity
public class Shelter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idShelter;
	
	private String shelterName;
	private String shelterNif;
	private String shelterEmail;
	private String shelterPassword;
	private int shelterAverageRating;
	private String shelterDescription;
	private String shelterAdress;
	
	//@OneToMany(cascade=CascadeType.ALL)
	private ArrayList<Animal> shelterAnimalList;
	//@OneToMany(cascade=CascadeType.ALL)
	private ArrayList<Adoption> shelterAdoptionRequests;
	
	
	//CONSTRUCTORS
	protected Shelter () {}
	
	public Shelter(String shelterName, String shelterNif, String shelterEmail, String shelterPassword,
			int shelterAverageRating, String shelterDescription, String shelterAdress) {
		this.shelterName = shelterName;
		this.shelterNif = shelterNif;
		this.shelterEmail = shelterEmail;
		this.shelterPassword = shelterPassword;
		this.shelterAverageRating = shelterAverageRating;
		this.shelterDescription = shelterDescription;
		this.shelterAdress = shelterAdress;
	}



	//GETTERS AND SETTERS
	public String getShelterName() {
		return shelterName;
	}
	public void setShelterName(String shelterName) {
		this.shelterName = shelterName;
	}
	public String getShelterNif() {
		return shelterNif;
	}
	public void setShelterNif(String shelterNif) {
		this.shelterNif = shelterNif;
	}
	public String getShelterEmail() {
		return shelterEmail;
	}
	public void setShelterEmail(String shelterEmail) {
		this.shelterEmail = shelterEmail;
	}
	public String getShelterPassword() {
		return shelterPassword;
	}
	public void setShelterPassword(String shelterPassword) {
		this.shelterPassword = shelterPassword;
	}
	public int getShelterAverageRating() {
		return shelterAverageRating;
	}
	public void setShelterAverageRating(int shelterAverageRating) {
		this.shelterAverageRating = shelterAverageRating;
	}
	public String getShelterDescription() {
		return shelterDescription;
	}
	public void setShelterDescription(String shelterDescription) {
		this.shelterDescription = shelterDescription;
	}
	public String getShelterAdress() {
		return shelterAdress;
	}
	public void setShelterAdress(String shelterAdress) {
		this.shelterAdress = shelterAdress;
	}
	public ArrayList<Animal> getShelterAnimalList() {
		return shelterAnimalList;
	}
	public void setShelterAnimalList(ArrayList<Animal> shelterAnimalList) {
		this.shelterAnimalList = shelterAnimalList;
	}
	public ArrayList<Adoption> getShelterAdoptionRequests() {
		return shelterAdoptionRequests;
	}
	public void setShelterAdoptionRequests(ArrayList<Adoption> shelterAdoptionRequests) {
		this.shelterAdoptionRequests = shelterAdoptionRequests;
	}
	
}
