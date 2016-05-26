package com.rcctv.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

/*
 * sends email messages using the JavaMailSender library in spring.
 */
public class SmtpMailSender implements MailSender {
	
	private static final Logger logger = LoggerFactory.getLogger(SmtpMailSender.class);

	private JavaMailSender javaMailSender;
	
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	@Async
	public void send(String to, String subject, String body)
			throws MessagingException {
		
		logger.info("Sending SMTP mail from thread " + Thread.currentThread().getName());    	

		// Sets the mime type.
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		// sets the message body, subject and sender.
		helper = new MimeMessageHelper(message, true); 
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true); 
		
		javaMailSender.send(message);

	}

}
