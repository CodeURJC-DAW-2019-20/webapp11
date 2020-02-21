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
	private String animalName;
	private int animalAge;
	private String animalType;
	private String animalSize;
	private String animalDescription;
	private String animalOwner;
	private boolean animalAdopted;
	private int animalDimensions;
	
	//CONSTRUCTORS
	protected Animal () {};
		
	public Animal(String animalPhoto, String animalName, int animalAge, String animalType,
			String animalSize, String animalDescription) {
		this.animalPhoto = animalPhoto;
		this.animalName = animalName;
		this.animalAge = animalAge;
		this.animalType = animalType;
		this.animalSize = animalSize;
		this.animalDescription = animalDescription;
		this.animalAdopted = false;
		this.animalDimensions= this.calcSizeAnimal();
		this.animalAdopted=false;
	}

	//GETTERS AND SETTERS
	public String getAnimalPhoto() {
		return animalPhoto;
	}
	public void setAnimalPhoto(String animalPhoto) {
		this.animalPhoto = animalPhoto;
	}
	public String getAnimlaName() {
		return animalName;
	}
	public void setAnimlaName(String animlaName) {
		this.animalName = animlaName;
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
	public int calcSizeAnimal() {
		int sizeAnimal =0;
		switch (this.animalSize) {
			case "irrelevant": sizeAnimal+=-3;
			case "xs": sizeAnimal+=0; break;
			case "s": sizeAnimal+=1; break;
			case "m": sizeAnimal+=4; break;
			case "l": sizeAnimal+=6; break;
			case "xl": sizeAnimal+=8; break;
		}
		switch (this.animalType) {
			case "bird": sizeAnimal+=-2; break;
			case "cat": sizeAnimal+=0; break;
			case "dog": sizeAnimal+=0; break;
			case "other": sizeAnimal+=2; break;
			case "reptile": sizeAnimal+=3; break;
		}
		return sizeAnimal;
		
	}
}
