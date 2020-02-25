package es.sidelab.animalshelter.controllers;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import es.sidelab.animalshelter.Adoption;
import es.sidelab.animalshelter.AdoptionRepository;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.SmtpMailSender;

@Controller
public class EvaluateAdoptionController extends ModelAttributeController {

	@Autowired
	SmtpMailSender smtpMailSender;

	@Autowired
	private AnimalRepository ar;

	@Autowired
	private AdoptionRepository ap;

	@RequestMapping("/evaluateAdoption/accept")
	public String accept(String animalName, HttpServletRequest request) throws MessagingException {

		Animal a = ar.findByAnimalName(animalName);
		Adoption adoption = ap.findByAnimal(a);

		adoption.confirmAdoption();
		ap.save(adoption);

		smtpMailSender.sendAccept("Adoption accepted", adoption.getUser().getUserEmail());

		return "index";
	}

	@RequestMapping("/evaluateAdoption/deny")
	public String deny(String animalName, HttpServletRequest request) throws MessagingException {

		Animal a = ar.findByAnimalName(animalName);
		a.setAnimalAdopted(false);
		Adoption adoption = ap.findByAnimal(a);

		adoption.confirmAdoption();
		ar.save(a);
		ap.save(adoption);

		smtpMailSender.sendDeny("Adoption rejected", adoption.getUser().getUserEmail());

		return "index";
	}

}