package es.sidelab.animalshelter.controllers;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.sidelab.animalshelter.SmtpMailSender;
import es.sidelab.animalshelter.UserShelterComponent;

@Controller
public class ContactUsMailController {

	@Autowired
	SmtpMailSender smtpMailSender;

	@Autowired
	private UserShelterComponent userShelterComponent;

	@RequestMapping("/contact")
	public String contactView(Model model, HttpServletRequest request) {
		model.addAttribute("logged", userShelterComponent.isLoggedUser());
		model.addAttribute("isShelter", request.isUserInRole("SHELTER"));
		return "contact";
	}

	@RequestMapping("/sendMail")
	public String sendMail(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email,
			@RequestParam(value = "subject") String subject, @RequestParam(value = "message") String message,
			Model model) throws MessagingException {

		smtpMailSender.sendMailContactUs(name, email, subject, "m.fernandezsu@alumnos.urjc.es", message);
		smtpMailSender.sendMailContactUs(name, email, subject, "aa.saleem.2017@alumnos.urjc.es", message);
		smtpMailSender.sendMailContactUs(name, email, subject, "b.castro.2018@alumnos.urjc.es", message);
		smtpMailSender.sendMailContactUs(name, email, subject, "r.cadenar.2019@alumnos.urjc.es", message);

		return "contact";
	}

}
