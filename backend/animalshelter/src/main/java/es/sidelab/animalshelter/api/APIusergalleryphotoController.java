package es.sidelab.animalshelter.api;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

@RestController
@RequestMapping("/api/user={id}")
public class APIusergalleryphotoController {

	private Map<Long, UserGalleryPhoto> userphotos = new ConcurrentHashMap<>();

	@GetMapping("/gallery")
	public Collection<UserGalleryPhoto> userphotos() {
		return userphotos.values();
	}

	@PostMapping("/addImage")
	@ResponseStatus(HttpStatus.CREATED)
	public UserGalleryPhoto newUserPhoto(@RequestBody UserGalleryPhoto userphoto) {
		userphotos.put(userphoto.getIdPhoto(), userphoto);
		return userphoto;
	}

	@PutMapping("/photo={idphoto}")
	public ResponseEntity<UserGalleryPhoto> updateUserPhoto(@PathVariable long id,
			@RequestBody UserGalleryPhoto updatedGalleryPhoto) {

		UserGalleryPhoto userphoto = userphotos.get(id);

		if (userphoto != null) {

			updatedGalleryPhoto.setIdPhoto(id);
			userphotos.put(id, updatedGalleryPhoto);

			return new ResponseEntity<>(updatedGalleryPhoto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/photo={idphoto}")
	public ResponseEntity<UserGalleryPhoto> getUserPhoto(@PathVariable long id) {

		UserGalleryPhoto userphoto = userphotos.get(id);

		if (userphoto != null) {
			return new ResponseEntity<>(userphoto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/photo={idphoto}")
	public ResponseEntity<UserGalleryPhoto> delteUserPhoto(@PathVariable long id) {

		UserGalleryPhoto userphoto = userphotos.remove(id);

		if (userphoto != null) {
			return new ResponseEntity<>(userphoto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
