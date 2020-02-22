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

	public void sendAutoMail(String to, String subject, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);// true indicates multipart message

		helper.setFrom(from); // <--- THIS IS IMPORTANT

		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);// true indicates body is html
		javaMailSender.send(message);
	}
	
	public void sendMailContactUs(String username, String subject, String to, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);// true indicates multipart message

		helper.setFrom(from); // <--- THIS IS IMPORTANT

		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(username + " has following issue: " + body, true);// true indicates body is html
		javaMailSender.send(message);
	}
}
