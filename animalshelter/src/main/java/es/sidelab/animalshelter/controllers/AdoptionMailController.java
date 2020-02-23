package es.sidelab.animalshelter.controllers;

import java.util.Optional;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.SmtpMailSender;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.WebUser;

@Controller
public class AdoptionMailController {

	@Autowired
	SmtpMailSender smtpMailSender;

	@Autowired
	UserShelterComponent u;

	@Autowired
	AnimalRepository animalRepository;

	@RequestMapping("/send/adopt/{idAnimal}/success")
	public String sendMail(@PathVariable long idAnimal) throws MessagingException {

		Optional<Animal> animal = animalRepository.findByIdAnimal(idAnimal);
		if (animal.isPresent()) {
			Animal a = animal.get();
			WebUser w = u.getUser();
			// a.getShelterOwner().getShelterEmail()

			smtpMailSender.sendAutoMail(w.getUserName(), w.getUserAdress(), w.getUserAge(), w.getUserDni(),
					w.getUserGarden(), w.getUserHouseSize(), w.getUserNumChildren(), w.getUserNumPeopleInHouse(),
					a.getAnimalName(), w.getUserEmail(), "Adoption Request", "m.fernandezsu@alumnos.urjc.es");

		}

		return "index";
	}

}
