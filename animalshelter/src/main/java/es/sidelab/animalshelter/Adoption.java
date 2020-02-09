package es.sidelab.animalshelter;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Adoption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAdoption;
	
	@NotNull
	private Date adoptionDate;
	@NotNull
	private String adoptionAnimalID;
	@NotNull
	private String aoptionUserID;
	
	
	//CONSTRUCTORS
	protected Adoption () {};
	
	public Adoption(int idAdoption, Date adoptionDate, String adoptionAnimalID, String aoptionUserID) {
		this.idAdoption = idAdoption;
		this.adoptionDate = adoptionDate;
		this.adoptionAnimalID = adoptionAnimalID;
		this.aoptionUserID = aoptionUserID;
	}


	//GETTERS AND SETTERS
	public Date getAdoptionDate() {
		return adoptionDate;
	}
	public void setAdoptionDate(Date adoptionDate) {
		this.adoptionDate = adoptionDate;
	}
	public String getAdoptionAnimalID() {
		return adoptionAnimalID;
	}
	public void setAdoptionAnimalID(String adoptionAnimalID) {
		this.adoptionAnimalID = adoptionAnimalID;
	}
	public String getAoptionUserID() {
		return aoptionUserID;
	}
	public void setAoptionUserID(String aoptionUserID) {
		this.aoptionUserID = aoptionUserID;
	}
	
}
