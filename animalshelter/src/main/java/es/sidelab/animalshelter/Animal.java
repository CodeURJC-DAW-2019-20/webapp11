package es.sidelab.animalshelter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAnimal;

	private String animalPhoto;
	private String animalName;
	private int animalAge;
	private String animalType;
	private String animalSize;
	private String animalDescription;
	private boolean animalAdopted;
	private int animalDimensions;

	@OneToOne
	private Adoption adoption;

	@ManyToOne
	private Shelter animalOwner;// Shelter owner
	
	@ManyToOne
	private User animalAdopter;// User owner

	// CONSTRUCTORS

	public Animal() {
	};

	public Animal(String animalPhoto, String animalName, int animalAge, String animalType, String animalSize,
			String animalDescription, Shelter animalOwner) {
		this.animalPhoto = animalPhoto;
		this.animalName = animalName;
		this.animalAge = animalAge;
		this.animalType = animalType;
		this.animalSize = animalSize;
		this.animalDescription = animalDescription;
		this.animalOwner = animalOwner;
		this.animalAdopted = false;
		this.animalDimensions = this.animalDimensions();
		this.adoption = null;
		this.animalAdopter = null;

	}

	// FUNCTIONS

	private int animalDimensions() {
		int sizeAnimal = 0;
		switch (this.animalSize) {
		case "irrelevant":
			sizeAnimal -= 3;
		case "xs":
			sizeAnimal += 0;
			break;
		case "s":
			sizeAnimal += 1;
			break;
		case "l":
			sizeAnimal += 6;
			break;
		case "xl":
			sizeAnimal += 8;
			break;
		default:
			sizeAnimal += 4;
			break;
		}
		switch (this.animalType) {
		case "bird":
			sizeAnimal -= 2;
			break;
		case "other":
			sizeAnimal += 2;
			break;
		case "reptile":
			sizeAnimal += 3;
			break;
		default:
			sizeAnimal += 0;
			break;
		}
		return sizeAnimal;
	}

	public void adoptAnimal() {
		this.animalAdopted = true;
	}

	// GETTERS AND SETTERS

	public boolean isAnimalAdopted() {
		return animalAdopted;
	}

	public String getAnimalPhoto() {
		return animalPhoto;
	}

	public String getAnimalName() {
		return animalName;
	}

	public int getAnimalAge() {
		return animalAge;
	}

	public String getAnimalType() {
		return animalType;
	}

	public String getAnimalSize() {
		return animalSize;
	}

	public String getAnimalDescription() {
		return animalDescription;
	}

	public Shelter getAnimalOwner() {
		return animalOwner;
	}

	public Adoption getAdoption() {
		return adoption;
	}

	public int getAnimalDimensions() {
		return animalDimensions;
	}

}
