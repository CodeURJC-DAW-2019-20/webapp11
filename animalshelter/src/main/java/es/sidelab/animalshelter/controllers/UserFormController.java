package es.sidelab.animalshelter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.sidelab.animalshelter.ShelterRepository;
import es.sidelab.animalshelter.User;
import es.sidelab.animalshelter.UserRepository;

@Controller
public class UserFormController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShelterRepository shelterRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@RequestMapping("/signuser")
	public String signuserView(Model model) {
		
		return "userform";
	}
	
	@RequestMapping("/create_user")
	public String createUser(Model model, User user) {
		
		
		if(userRepository.findByUserEmail(user.getUserEmail()).size() > 0 || 
			shelterRepository.findByShelterEmail(user.getUserEmail()).size() > 0) {
			
			return "userform";
		} else {
			user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
			userRepository.save(user);
			return "index";
		}
	}
	
	
}