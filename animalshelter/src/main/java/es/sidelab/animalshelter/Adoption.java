package es.sidelab.animalshelter;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adoption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAdoption;
	
	private Date adoptionDate;
	private String adoptionAnimalID;
	private String aoptionUserID;
	
	
	//CONSTRUCTORS
	protected Adoption () {};
	
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
