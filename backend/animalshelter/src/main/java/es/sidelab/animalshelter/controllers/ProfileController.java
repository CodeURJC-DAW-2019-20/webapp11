package es.sidelab.animalshelter.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import es.sidelab.animalshelter.UserGalleryPhoto;
import es.sidelab.animalshelter.UserGalleryPhotoRepository;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.WebUserRepository;
import es.sidelab.animalshelter.services.UserGalleryService;
import es.sidelab.animalshelter.services.WebUserService;

@Controller
public class ProfileController extends ModelAttributeController {

	@Autowired
	private UserShelterComponent loggeduser;

	@Autowired
	private UserGalleryPhotoRepository ugpr;

	@Autowired
	private ImageService imgService;
	@Autowired
	private WebUserService service;
	@Autowired
	private UserGalleryService servicegallery;

	@Autowired
	private WebUserRepository ur;

	@RequestMapping("/profile")
	public String profileView(Model model, HttpServletRequest request) {

		WebUser lu = (WebUser) loggeduser.getLoggedUser();
		service.save(lu);

		List<String> gallery = new ArrayList<>();
		gallery = ur.getUserGalleryPhotos(lu);

		model.addAttribute("id", lu.getIdUser());
		model.addAttribute("username", lu.getUserName());
		model.addAttribute("housesize", lu.getUserHouseSize());
		model.addAttribute("garden", lu.getUserGarden());
		model.addAttribute("nkids", lu.getUserNumChildren());
		model.addAttribute("pinhouse", lu.getUserNumPeopleInHouse());
		model.addAttribute("gallery", gallery);

		
		return "profile";
	}

	@RequestMapping("/addImage")
	public String updateProfile(@RequestParam MultipartFile galleryPhoto, Model model, HttpServletRequest request)
			throws IOException {

		WebUser lu = (WebUser) loggeduser.getLoggedUser();
		service.save(lu);

		imgService.saveUserGalleryImage("user", galleryPhoto);

		String photo = "/images/user/" + galleryPhoto.getOriginalFilename();
		UserGalleryPhoto gp = new UserGalleryPhoto(photo);
		gp.setGalleryOwner(lu);
		ugpr.save(gp);
		servicegallery.save(gp);

		List<String> gallery = new ArrayList<>();
		gallery = ur.getUserGalleryPhotos(lu);

		model.addAttribute("id", lu.getIdUser());
		model.addAttribute("username", lu.getUserName());
		model.addAttribute("housesize", lu.getUserHouseSize());
		model.addAttribute("garden", lu.getUserGarden());
		model.addAttribute("nkids", lu.getUserNumChildren());
		model.addAttribute("pinhouse", lu.getUserNumPeopleInHouse());
		model.addAttribute("gallery", gallery);

		return "profile";
	}

}
