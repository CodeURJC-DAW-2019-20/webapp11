package es.sidelab.animalshelter.api;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.sidelab.animalshelter.SmtpMailSender;

@Controller
@RequestMapping("/api/contact")
public class APIContactUsMailController {

	@Autowired
	SmtpMailSender smtpMailSender;

	@RequestMapping("/sendMail")
	public List<String> sendMail(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email,
			@RequestParam(value = "subject") String subject, @RequestParam(value = "message") String message,
			Model model) throws MessagingException {
		List<String> entireEmail = new ArrayList<>();
		entireEmail.add(name);
		entireEmail.add(email);
		entireEmail.add(subject);
		entireEmail.add(message);
		
		smtpMailSender.sendMailContactUs(name, email, subject, "m.fernandezsu@alumnos.urjc.es", message);
		smtpMailSender.sendMailContactUs(name, email, subject, "aa.saleem.2017@alumnos.urjc.es", message);
		smtpMailSender.sendMailContactUs(name, email, subject, "b.castro.2018@alumnos.urjc.es", message);
		smtpMailSender.sendMailContactUs(name, email, subject, "r.cadenar.2019@alumnos.urjc.es", message);

		return entireEmail;
	}

}