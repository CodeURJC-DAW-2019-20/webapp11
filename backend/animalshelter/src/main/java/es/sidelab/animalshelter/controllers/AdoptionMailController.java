package es.sidelab.animalshelter.controllers;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.sidelab.animalshelter.Adoption;
import es.sidelab.animalshelter.AdoptionRepository;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.SmtpMailSender;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.services.AdoptionService;
import es.sidelab.animalshelter.services.AnimalService;

@Controller
public class AdoptionMailController {

	@Autowired
	SmtpMailSender smtpMailSender;

	@Autowired
	UserShelterComponent u;

	@Autowired
	AnimalService animalRepository;

	@Autowired
	AdoptionService adoptionRepository;

	@RequestMapping("/send/adopt")
	public String sendMail(String animalName) throws MessagingException {

		WebUser w = u.getUser();
		animalName = animalName.substring(0, animalName.length() - 1);
		Animal animal = animalRepository.findByAnimalName(animalName);
		String shelterEmail = animal.getShelterOwner().getShelterEmail();

		Adoption adoption = new Adoption(true);
		adoption.setAnimal(animal);
		adoption.setUser(w);
		adoptionRepository.save(adoption);
		animal.setAnimalAdopted(true);
		animalRepository.save(animal);

		smtpMailSender.sendAutoMail(w.getUserName(), animal.getAnimalName(), w.getUserEmail(), "Adoption Request",
				shelterEmail);

		return "index";
	}

}
