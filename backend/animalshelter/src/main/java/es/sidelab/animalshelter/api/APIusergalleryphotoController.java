package es.sidelab.animalshelter.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import es.sidelab.animalshelter.UserGalleryPhoto;
import es.sidelab.animalshelter.services.UserGalleryService;


@RestController
@RequestMapping("/api/user/{id}")
public class APIusergalleryphotoController {

	@Autowired
	private UserGalleryService service;

	@GetMapping("/gallery")
	public Collection<UserGalleryPhoto> userphotos() {
		return service.findAll();
	}

	@PostMapping("/addImage")
	@ResponseStatus(HttpStatus.CREATED)
	public UserGalleryPhoto newUserPhoto(@RequestBody UserGalleryPhoto userphoto) {
		service.save(userphoto);
		return userphoto;
	}

	@PutMapping("/{idphoto}")
	public ResponseEntity<UserGalleryPhoto> updateUserPhoto(@PathVariable long id,
			@RequestBody UserGalleryPhoto updatedGalleryPhoto) {

		UserGalleryPhoto userphoto = service.findByGalleryId(id);

		if (userphoto != null) {

			updatedGalleryPhoto.setIdPhoto(id);
			service.update(updatedGalleryPhoto);

			return new ResponseEntity<>(updatedGalleryPhoto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{idphoto}")
	public ResponseEntity<UserGalleryPhoto> getUserPhoto(@PathVariable long id) {

		UserGalleryPhoto userphoto = service.findByGalleryId(id);

		if (userphoto != null) {
			return new ResponseEntity<>(userphoto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{idphoto}")
	public ResponseEntity<UserGalleryPhoto> delteUserPhoto(@PathVariable long id) {

		UserGalleryPhoto userphoto = service.findByGalleryId(id);
		if (userphoto.getIdPhoto() == id) {
			service.delete(id);
			return new ResponseEntity<>(userphoto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}
