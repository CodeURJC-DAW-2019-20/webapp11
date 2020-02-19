package es.sidelab.animalshelter;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

	@Autowired
	SmtpMailSender smtpMailSender;

	@RequestMapping(path = "/mail/send")
	public void sendMail() throws MessagingException {
	    smtpMailSender.sendMail("m.fernandezsu@alumnos.urjc.es", "testmail", "Hola guapa :)");
	}
}

