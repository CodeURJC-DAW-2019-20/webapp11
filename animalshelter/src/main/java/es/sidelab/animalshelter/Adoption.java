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
	private int idAdoption;

	private java.sql.Date adoptionDate;

	@OneToOne(mappedBy = "adoption")
	private Animal adoptionAnimal;

	@ManyToOne
	private User adoptionUser;

	@ManyToOne
	private Shelter shelterOwner;

	private boolean inCourse;

	// CONSTRUCTORS

	public Adoption() {
	}

	public Adoption(Animal adoptionAnimal, User adoptionUser) {
		this.adoptionDate = Date.valueOf(LocalDate.now());
		this.adoptionAnimal = adoptionAnimal;
		this.adoptionUser = adoptionUser;
		this.shelterOwner = this.adoptionAnimal.getAnimalOwner();
		this.inCourse = true;
	}

	// FUNCTION

	public void confirmAdoption() {
		this.inCourse = false;
		this.shelterOwner.removeRequest(this);
		//this.shelterOwner = null;
		this.adoptionAnimal.adoptAnimal(); // put animal.animalAdopted to true
		this.adoptionUser.addAnimal(this.adoptionAnimal); // add this animal to user.animalList
	}

	public String readAdoptionDate() {
		// return adoptionDate.toGMTString(); //deprecated
		return adoptionDate.toString();
	}

	// GETTERS AND SETTERS

	public boolean isInCourse() {
		return inCourse;
	}

	public java.sql.Date getAdoptionDate() {
		return adoptionDate;
	}

	public Animal getAdoptionAnimal() {
		return adoptionAnimal;
	}

	public User getAdoptionUser() {
		return adoptionUser;
	}

	public Shelter getShelterOwner() {
		return shelterOwner;
	}

}