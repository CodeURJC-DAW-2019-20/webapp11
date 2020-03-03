package es.sidelab.animalshelter.api;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
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
import es.sidelab.animalshelter.Adoption;

@RestController
@RequestMapping("/api")
public class APIadoptionController {

	private Map<Long, Adoption> adoptions = new ConcurrentHashMap<>();
	private AtomicLong lastIdadoptions = new AtomicLong();

	@GetMapping("/")
	public Collection<Adoption> adoptions() {
		return adoptions.values();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Adoption newAdoption(@RequestBody Adoption adoption) {

		long id = lastIdadoptions.incrementAndGet();
		adoption.setId(id); // Review setId
		adoptions.put(id, adoption);

		return adoption;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Adoption> updateAdoption(@PathVariable long id, @RequestBody Adoption updatedAdoption) {

		Adoption adoption = adoptions.get(id);

		if (adoption != null) {

			updatedAdoption.setId(id); // Review setId
			adoptions.put(id, updatedAdoption);

			return new ResponseEntity<>(updatedAdoption, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Adoption> getItem(@PathVariable long id) {

		Adoption adoption = adoptions.get(id);

		if (adoption != null) {
			return new ResponseEntity<>(adoption, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Adoption> borraItem(@PathVariable long id) {

		Adoption adoption = adoptions.remove(id);

		if (adoption != null) {
			return new ResponseEntity<>(adoption, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
