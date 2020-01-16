package com.nt.utility;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class MailUtility {
	@Autowired
	private JavaMailSender sender;
	/**
	 * 	This method used for sending mail 
	 * @param to
	 * @param body
	 * @param subject
	 * @throws MessagingException 
	 */
	public void sendMail(String to,String body,String subject) throws MessagingException {
		System.out.println("MailUtility.sendMail()"); 
		MimeMessage msg=sender.createMimeMessage();
		  MimeMessageHelper helper=new MimeMessageHelper(msg,true);
		  helper.setTo(to);
		  helper.setSubject(subject);
		  helper.setText(body,true);
		  sender.send(msg);
	}
	
	
	public void sendEmailForPwd(String email,String body,String subject) {
		System.out.println("MailUtility.sendEmailForPwd()");
		SimpleMailMessage msg=new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject(subject);
		msg.setText(body);
		sender.send(msg);
	}
}
