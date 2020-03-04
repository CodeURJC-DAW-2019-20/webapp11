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
import es.sidelab.animalshelter.WebUser;

@RestController
@RequestMapping("/api/users")
public class APIwebuserController {

	private Map<Long, WebUser> webusers = new ConcurrentHashMap<>();

	@GetMapping("/")
	public Collection<WebUser> webusers() {
		return webusers.values();
	}

	@PostMapping("/addUser")
	@ResponseStatus(HttpStatus.CREATED)
	public WebUser newWebUser(@RequestBody WebUser webuser) {
		webusers.put(webuser.getIdUser(), webuser);
		return webuser;
	}

	@PutMapping("/user={id}")
	public ResponseEntity<WebUser> updateWebUser(@PathVariable long id, @RequestBody WebUser updatedWebUser) {

		WebUser webuser = webusers.get(id);

		if (webuser != null) {

			updatedWebUser.setIdUser(id);
			webusers.put(id, updatedWebUser);

			return new ResponseEntity<>(updatedWebUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/user={id}")
	public ResponseEntity<WebUser> getWebUser(@PathVariable long id) {

		WebUser webuser = webusers.get(id);

		if (webuser != null) {
			return new ResponseEntity<>(webuser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/user={id}")
	public ResponseEntity<WebUser> deleteWebUser(@PathVariable long id) {

		WebUser webuser = webusers.remove(id);

		if (webuser != null) {
			return new ResponseEntity<>(webuser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
