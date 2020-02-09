package es.sidelab.animalshelter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAnimal;
	
	@NotNull
	private String animalPhoto;
	@NotNull
	private String animalName;
	@NotNull
	private int animalAge;
	@NotNull
	private String animalType;
	@NotNull
	private String animalSize;
	@NotNull
	private String animalDescription;
	@NotNull
	private String animalOwner;
	@NotNull
	private boolean animalAdopted;
	
	
	//CONSTRUCTORS
	protected Animal () {};
		
	public Animal(String animalPhoto, String animlaName, int animalAge, String animalType,
			String animalSize, String animalDescription, String animalOwner, boolean animalAdopted) {
		this.animalPhoto = animalPhoto;
		this.animalName = animlaName;
		this.animalAge = animalAge;
		this.animalType = animalType;
		this.animalSize = animalSize;
		this.animalDescription = animalDescription;
		this.animalOwner = animalOwner;
		this.animalAdopted = animalAdopted;
	}



	//GETTERS AND SETTERS
	public String getAnimalPhoto() {
		return animalPhoto;
	}
	public void setAnimalPhoto(String animalPhoto) {
		this.animalPhoto = animalPhoto;
	}
	public String getAnimalName() {
		return animalName;
	}
	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}
	public int getAnimalAge() {
		return animalAge;
	}
	public void setAnimalAge(int animalAge) {
		this.animalAge = animalAge;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	public String getAnimalSize() {
		return animalSize;
	}
	public void setAnimalSize(String animalSize) {
		this.animalSize = animalSize;
	}
	public String getAnimalDescription() {
		return animalDescription;
	}
	public void setAnimalDescription(String animalDescription) {
		this.animalDescription = animalDescription;
	}
	public String getAnimalOwner() {
		return animalOwner;
	}
	public void setAnimalOwner(String animalOwner) {
		this.animalOwner = animalOwner;
	}
	public boolean isAnimalAdopted() {
		return animalAdopted;
	}
	public void setAnimalAdopted(boolean animalAdopted) {
		this.animalAdopted = animalAdopted;
	}
	
}
