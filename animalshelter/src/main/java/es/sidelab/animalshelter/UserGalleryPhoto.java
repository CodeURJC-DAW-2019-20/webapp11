package es.sidelab.animalshelter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserGalleryPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPhoto;

	private String photo;

	@ManyToOne
	private User userOwner;// User owner

	// CONSTRUCTORS

	public UserGalleryPhoto() {
	}

	public UserGalleryPhoto(String photo) {
		this.photo = photo;
	}

	// GETTERS AND SETTERS
	
	public void setUserOwner (User u) {
		this.userOwner = u;
	}

	public String getPhoto() {
		return photo;
	}

	public User getUserOwner() {
		return userOwner;
	}

}
