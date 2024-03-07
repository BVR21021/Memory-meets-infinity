package com.momory.serviceImpl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momory.util.ApplicationUtils;


@Service
public class EmailService {

	@Autowired
	private ApplicationUtils utils;

	private Message getMessage() {
		Message message = null;

		try {
			final String username = utils.mailUsername;
			final String password = utils.mailPassword;
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "465");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.socketFactory.port", "465");
			prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}

	public void sendOtp(String email, String OtpMessage) {

		Message message = getMessage();

		try {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Otp For Login");
			message.setText(OtpMessage);
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}
	
	public String ForgotPassword(String name, String link) {

		return "Dear " + name + ", \r\n" + "\r\n"
				+ "We have received your request to reset your password for the Hirelink Portal. Please follow the steps below to create a new password and access your account."
				+ "\r\n" + "Click on this link to go to the password reset page: " + link + "\r\n"
				+ "You should be able to log in to the Hirelink Portal with your new password. If you have any issues or questions,"
				+ "\r\n" + "please contact us at info@visualit.in" + "\r\n"
				+ "Thank you for your cooperation and understanding.";
	}

	public String PasswordChange(String name, String email) {
		return "Hi " + name + ", \r\n" + "\r\n" + "The password for your Hirelink account " + email
				+ "  was changed on " + new java.util.Date().toString() + ". \r\n" + "\r\n"
				+ "If this was you, then you can safely ignore this mail." + "\r\n" + "\r\n"
//				+ "Didn't change your password?"+"\r\n"+"\r\n"
//				+ "Recover your account here : "+"\r\n"
//				+ "https://refrly.com/login/#/forgot-password"+"\r\n"+"\r\n"
				+ "\r\n" + "Thanks & Regards" + "\r\n" + "Team Hirelink";
	}

}
