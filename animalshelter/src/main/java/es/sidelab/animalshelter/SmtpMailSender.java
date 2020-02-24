package es.sidelab.animalshelter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SmtpMailSender {

	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendAutoMail(String username, String adress, int age, String dni, String garden, String houseSize,
			int children, int peopleInHouse, String animalname, String email, String subject, String to)
			throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);// true indicates multipart message

		helper.setFrom(from); // <--- THIS IS IMPORTANT

		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(username + " with following data: <br>" + "<br>Adress: " + adress + "<br>Age: " + age
				+ "<br>DNI: " + dni + "<br>Garden Size: " + garden + "<br>House Size: " + houseSize
				+ "<br>Number Of Children: " + children + "<br>People In House: " + peopleInHouse
				+ "<br><br>Has requested to adopt: " + animalname + "<br>" + "Response to: " + email, true);// true //
																											// indicates
																											// // body
																											// // is //
																											// html
		javaMailSender.send(message);
	}

	public void sendMailContactUs(String username, String email, String subject, String to, String body)
			throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);// true indicates multipart message

		helper.setFrom(from); // <--- THIS IS IMPORTANT

		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(username + " has following issue:<br>" + body + "<br>" + "Response to: " + email, true);// true
																												// indicates
																												// body
																												// is //
																												// html
		javaMailSender.send(message);
	}
}
