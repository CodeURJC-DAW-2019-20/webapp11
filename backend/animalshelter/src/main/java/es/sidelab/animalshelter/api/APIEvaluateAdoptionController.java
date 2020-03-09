package es.sidelab.animalshelter.api;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.sidelab.animalshelter.Adoption;
import es.sidelab.animalshelter.AdoptionRepository;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.SmtpMailSender;

@RestController
@RequestMapping("/api/evaluateadoptions")
public class APIEvaluateAdoptionController {

	@Autowired
	SmtpMailSender smtpMailSender;

	@Autowired
	private AnimalRepository ar;

	@Autowired
	private AdoptionRepository ap;

	@GetMapping("/accept")
	public Adoption accept(String animalName, HttpServletRequest request) throws MessagingException {

		Animal a = ar.findByAnimalName(animalName);
		Adoption adoption = ap.findByAnimal(a);

		adoption.confirmAdoption();
		ap.save(adoption);

		smtpMailSender.sendAccept("Adoption accepted", adoption.getUser().getUserEmail());

		return adoption;
	}

	@GetMapping("/deny")
	public Adoption deny(String animalName, HttpServletRequest request) throws MessagingException {

		Animal a = ar.findByAnimalName(animalName);
		a.setAnimalAdopted(false);
		Adoption adoption = ap.findByAnimal(a);

		adoption.confirmAdoption();
		ar.save(a);
		ap.save(adoption);

		smtpMailSender.sendDeny("Adoption rejected", adoption.getUser().getUserEmail());

		return adoption;
	}

}
