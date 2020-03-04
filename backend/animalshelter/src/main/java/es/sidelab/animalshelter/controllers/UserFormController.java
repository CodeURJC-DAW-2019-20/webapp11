package es.sidelab.animalshelter.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import es.sidelab.animalshelter.ShelterRepository;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.WebUserRepository;

@Controller
public class UserFormController {

	@Autowired
	private WebUserRepository userRepository;

	@Autowired
	private ShelterRepository shelterRepository;

	@Autowired
	private UserShelterComponent userShelterComponent;

	@Autowired
	private ImageService imgService;

	@RequestMapping("/signuser")
	public String signuserView(Model model, HttpServletRequest request) {
		model.addAttribute("logged", userShelterComponent.isLoggedUser());
		model.addAttribute("isShelter", request.isUserInRole("SHELTER"));
		return "userform";
	}

	@PostMapping("/createUser")
	public String createUser(Model model, HttpServletRequest request, @RequestParam MultipartFile userPhoto,
			@RequestParam String userName, @RequestParam String userDni, @RequestParam int userAge,
			@RequestParam String userAdress, @RequestParam String userHouseSize, @RequestParam String userGarden,
			@RequestParam int userNumChildren, @RequestParam int userNumPeopleInHouse, @RequestParam String userEmail,
			@RequestParam String userPassword) throws IOException {

		if (userRepository.findByUserEmail(userEmail) != null
				|| shelterRepository.findByShelterEmail(userEmail) != null) {

			model.addAttribute("logged", userShelterComponent.isLoggedUser());
			model.addAttribute("isShelter", request.isUserInRole("SHELTER"));

			return "userform";
		} else {

			WebUser user = new WebUser(userName, userDni, userAge, userAdress, userHouseSize, userGarden,
					userNumChildren, userNumPeopleInHouse, userEmail, userPassword);

			userRepository.save(user);
			imgService.saveImage("user", user.getIdUser(), userPhoto);
			model.addAttribute("logged", userShelterComponent.isLoggedUser());
			model.addAttribute("isShelter", request.isUserInRole("SHELTER"));

			return "index";
		}
	}

}
