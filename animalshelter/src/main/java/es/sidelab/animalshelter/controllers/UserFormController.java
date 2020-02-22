package es.sidelab.animalshelter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.sidelab.animalshelter.ShelterRepository;
import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.WebUserRepository;

@Controller
public class UserFormController {

	@Autowired
	private WebUserRepository userRepository;

	@Autowired
	private ShelterRepository shelterRepository;

	@RequestMapping("/signuser")
	public String signuserView(Model model) {

		return "userform";
	}

	@RequestMapping("/createUser")
	public String createUser(Model model, WebUser user) {
		if (userRepository.findByUserEmail(user.getUserEmail()) != null
				|| shelterRepository.findByShelterEmail(user.getUserEmail()) != null) {

			return "userform";
		} else {
			userRepository.save(user);
			return "index";
		}
	}

}