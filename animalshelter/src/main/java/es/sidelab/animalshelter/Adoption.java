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
	private String adoptionUserID;
	
	
	//CONSTRUCTORS
	protected Adoption () {}
	
	public Adoption(int idAdoption, Date adoptionDate, String adoptionAnimalID, String aoptionUserID) {
		this.idAdoption = idAdoption;
		this.adoptionDate = adoptionDate;
		this.adoptionAnimalID = adoptionAnimalID;
		this.adoptionUserID = aoptionUserID;
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
	public String getAdoptionUserID() {
		return adoptionUserID;
	}
	public void setAdoptionUserID(String aoptionUserID) {
		this.adoptionUserID = aoptionUserID;
	}
	
}
