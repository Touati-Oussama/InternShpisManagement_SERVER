package tn.enicarthage.internshipsmanagement.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
	

	private  final JavaMailSender mailSender;
	
	public void sendEmail(String to,String subject,String text)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		 	message.setFrom("touati.oussama.2022@gmail.com");
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText(text);
	        mailSender.send(message);
	}

}
