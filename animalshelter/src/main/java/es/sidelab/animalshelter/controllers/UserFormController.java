package es.sidelab.animalshelter.controllers;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import es.sidelab.animalshelter.ShelterRepository;
import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.WebUserRepository;

@Controller
public class UserFormController {

	@Autowired
	private WebUserRepository userRepository;

	@Autowired
	private ShelterRepository shelterRepository;

	@Autowired
	private ImageService imgService;

	@RequestMapping("/signuser")
	public String signuserView(Model model) {

		return "userform";
	}

	@PostMapping("/createUser")
	public String createUser(Model model, @RequestParam MultipartFile userPhoto, @RequestParam String userName,
			@RequestParam String userDni, @RequestParam int userAge, @RequestParam String userAdress,
			@RequestParam String userHouseSize, @RequestParam String userGarden, @RequestParam int userNumChildren,
			@RequestParam int userNumPeopleInHouse, @RequestParam String userEmail, @RequestParam String userPassword)
			throws IOException {
		if (userRepository.findByUserEmail(userEmail) != null
				|| shelterRepository.findByShelterEmail(userEmail) != null) {

			return "userform";
		} else {
			WebUser user = new WebUser(userName, userDni, userAge, userAdress, userHouseSize, userGarden,
					userNumChildren, userNumPeopleInHouse, userEmail, userPassword);
			userRepository.save(user);

			imgService.saveImage("user", user.getUserId(), userPhoto);
			return "index";
		}
	}

}
