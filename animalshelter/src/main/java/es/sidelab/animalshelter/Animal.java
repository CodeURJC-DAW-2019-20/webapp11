package es.sidelab.animalshelter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAnimal;
	
	private String animalPhoto;
	private String animlaName;
	private int animalAge;
	private String animalType;
	private String animalSize;
	private String animalDescription;
	private String animalOwner;
	private boolean animalAdopted;
	
	
	//CONSTRUCTORS
	protected Animal () {};
		
	//GETTERS AND SETTERS
	public String getAnimalPhoto() {
		return animalPhoto;
	}
	public void setAnimalPhoto(String animalPhoto) {
		this.animalPhoto = animalPhoto;
	}
	public String getAnimlaName() {
		return animlaName;
	}
	public void setAnimlaName(String animlaName) {
		this.animlaName = animlaName;
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
