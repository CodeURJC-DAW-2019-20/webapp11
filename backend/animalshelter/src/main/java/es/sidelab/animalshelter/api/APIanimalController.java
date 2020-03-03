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
import es.sidelab.animalshelter.Animal;

@RestController
@RequestMapping("/api")
public class APIanimalController {

	private Map<Long, Animal> animals = new ConcurrentHashMap<>();
	private AtomicLong lastIdanimals = new AtomicLong();

	@GetMapping("/")
	public Collection<Adoption> adoptions() {
		return adoptions.values();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Item nuevoItem(@RequestBody Item item) {

		long id = lastId.incrementAndGet();
		item.setId(id);
		items.put(id, item);

		return item;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Item> actulizaItem(@PathVariable long id, @RequestBody Item itemActualizado) {

		Item item = items.get(id);

		if (item != null) {

			itemActualizado.setId(id);
			items.put(id, itemActualizado);

			return new ResponseEntity<>(itemActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> getItem(@PathVariable long id) {

		Item item = items.get(id);

		if (item != null) {
			return new ResponseEntity<>(item, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Item> borraItem(@PathVariable long id) {

		Item item = items.remove(id);

		if (item != null) {
			return new ResponseEntity<>(item, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
