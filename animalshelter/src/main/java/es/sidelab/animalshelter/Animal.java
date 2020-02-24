package es.sidelab.animalshelter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idAnimal;

	private String animalPhoto;
	private String animalName;
	private int animalAge;
	private String animalType;
	private String animalSize;
	private String animalDescription;
	private boolean animalAdopted;
	private int animalDimensions;

	@ManyToOne
	private Shelter shelterOwner;

	// CONSTRUCTORS

	public Animal() {
	};

	public Animal(String animalName, int animalAge, String animalType, String animalSize, String animalDescription) {
		this.animalName = animalName;
		this.animalAge = animalAge;
		this.animalType = animalType;
		this.animalSize = animalSize;
		this.animalDescription = animalDescription;
		this.animalAdopted = false;
		this.animalDimensions = this.animalDimensions();
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

	public long getIdAnimal() {
		return idAnimal;
	}

	public Shelter getShelterOwner() {
		return shelterOwner;
	}

	public void setShelterOwner(Shelter shelterOwner) {
		this.shelterOwner = shelterOwner;
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

	public int getAnimalDimensions() {
		return animalDimensions;
	}

	public void setIdAnimal(long idAnimal) {
		this.idAnimal = idAnimal;
	}

	public void setAnimalPhoto(String animalPhoto) {
		this.animalPhoto = animalPhoto;
	}

	@Override
	public String toString() {
		return "Animal [id=" + idAnimal + ", animalPhoto=" + animalPhoto + ", animalName=" + animalName + ", animalAge="
				+ animalAge + ", animalType=" + animalType + ", animalSize=" + animalSize + ", animalDescription="
				+ animalDescription + "]";
	}

}