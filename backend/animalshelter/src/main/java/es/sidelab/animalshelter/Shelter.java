package es.sidelab.animalshelter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Shelter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idShelter;

	private String shelterName;
	private String shelterNif;
	private String shelterEmail;
	private int shelterAverageRating;
	private int numVotes;
	private String shelterDescription;
	private String shelterAdress;
	private String role;
	@JsonIgnore
	private String shelterPassword;

	// CONSTRUCTORS

	public Shelter() {

	}

	public Shelter(String shelterName, String shelterNif, String shelterEmail, String shelterPassword,
			String shelterDescription, String shelterAdress) {
		this.shelterName = shelterName;
		this.shelterNif = shelterNif;
		this.shelterEmail = shelterEmail;
		this.shelterPassword = new BCryptPasswordEncoder().encode(shelterPassword);
		this.shelterDescription = shelterDescription;
		this.shelterAdress = shelterAdress;
		this.shelterAverageRating = 0;
		this.numVotes = 0;
		this.role = "SHELTER";
	}

	// FUNCTIONS

	public void updateShelterAverageRating(int newVote) {
		int auxAverage = this.getShelterAverageRating();
		int auxVotes = this.getNumVotes();
		this.numVotes++;
		this.shelterAverageRating = ((auxAverage * auxVotes) + newVote) / this.numVotes;
	}

	// GETTERS AND SETTERS

	public int getShelterAverageRating() {
		return shelterAverageRating;
	}

	public void setIdShelter(long idShelter) {
		this.idShelter = idShelter;
	}

	public long getIdShelter() {
		return idShelter;
	}

	public String getShelterAdress() {
		return shelterAdress;
	}

	public String getShelterName() {
		return shelterName;
	}

	public String getShelterNif() {
		return shelterNif;
	}

	public String getShelterEmail() {
		return shelterEmail;
	}

	public String getShelterPassword() {
		return shelterPassword;
	}

	public int getNumVotes() {
		return numVotes;
	}

	public String getShelterDescription() {
		return shelterDescription;
	}

	public void setPassword(String password) {
		this.shelterPassword = new BCryptPasswordEncoder().encode(password);
	}

	public String getRole() {
		return role;
	}
}
