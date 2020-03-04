package es.sidelab.animalshelter;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Adoption {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idAdoption;
	private boolean inCourse;
	private Date adoptionDate;

	@OneToOne
	private Animal animal;

	@ManyToOne
	private WebUser user;

	// CONSTRUCTORS

	public Adoption() {

	}

	public Adoption(boolean t) {
		this.adoptionDate = Date.valueOf(LocalDate.now());
		this.inCourse = t;
	}

	// FUNCTION

	public void confirmAdoption() {
		this.inCourse = false;
		// this.shelterOwner.removeRequest(this);
		// this.shelterOwner = null;
		// this.animal.adoptAnimal(); // put animal.animalAdopted to true
		// this.adoptionUser.addAnimal(this.adoptionAnimal); // add this animal to
		// user.animalList
	}

	public String readAdoptionDate() {
		// return adoptionDate.toGMTString(); //deprecated
		return adoptionDate.toString();
	}

	// GETTERS AND SETTERS

	public boolean isInCourse() {
		return inCourse;
	}

	public void setIdAdoption(long idAdoption) {
		this.idAdoption = idAdoption;
	}

	public long getIdAdoption() {
		return idAdoption;
	}

	public java.sql.Date getAdoptionDate() {
		return adoptionDate;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public WebUser getUser() {
		return user;
	}

	public void setUser(WebUser user) {
		this.user = user;
	}

}
