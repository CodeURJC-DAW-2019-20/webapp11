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

import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.services.WebUserService;

@Controller
public class UserFormController extends ModelAttributeController{
	
	@Autowired
	private ImageService imgService;
	
	@Autowired
	private WebUserService userService;

	@RequestMapping("/signuser")
	public String signuserView(Model model) {
		return "userform";
	}

	@PostMapping("/createUser")
	public String createUser(Model model, HttpServletRequest request, @RequestParam MultipartFile userPhoto,
			@RequestParam String userName, @RequestParam String userDni, @RequestParam int userAge,
			@RequestParam String userAdress, @RequestParam String userHouseSize, @RequestParam String userGarden,
			@RequestParam int userNumChildren, @RequestParam int userNumPeopleInHouse, @RequestParam String userEmail,
			@RequestParam String userPassword) throws IOException {

		WebUser user = new WebUser(userName, userDni, userAge, userAdress, userHouseSize, userGarden,
				userNumChildren, userNumPeopleInHouse, userEmail, userPassword);
		
		if (userService.save(user)) {
			imgService.saveImage("user", user.getIdUser(), userPhoto);
			user.setUserphoto("image-" + user.getIdUser() + ".jpg");
			userService.update(user);
			return "index";
		} else {
			return "userform";
		}
	}

}
