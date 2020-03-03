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
	private long idPhoto;
	private String photo;

	@ManyToOne
	private WebUser galleryOwner;

	// CONSTRUCTORS

	public UserGalleryPhoto() {
	}

	public UserGalleryPhoto(String photo) {
		this.photo = photo;
	}

	// GETTERS AND SETTERS

	public String getPhoto() {
		return photo;
	}
	

	public WebUser getGalleryOwner() {
		return galleryOwner;
	}

	public void setGalleryOwner(WebUser galleryOwner) {
		this.galleryOwner = galleryOwner;
	}

}
