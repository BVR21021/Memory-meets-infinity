package com.momory.util;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationUtils {
	public static String mailHost;
	public static String mailPort;
	public static String mailUsername;
	public static String mailPassword;
	public static String mailAuth;

	public static String getMailHost() {
		return mailHost;
	}

	@Value("${spring.mail.host}")
	public void setMailHost(String mailHost) {
		ApplicationUtils.mailHost = mailHost;
	}

	public static String getMailPort() {
		return mailPort;
	}

	@Value("${spring.mail.port}")
	public void setMailPort(String mailPort) {
		ApplicationUtils.mailPort = mailPort;
	}

	public static String getMailUsername() {
		return mailUsername;
	}

	@Value("${spring.mail.username}")
	public void setMailUsername(String mailUsername) {
		ApplicationUtils.mailUsername = mailUsername;
	}

	public static String getMailPassword() {
		return mailPassword;
	}

	@Value("${spring.mail.password}")
	public void setMailPassword(String mailPassword) {
		ApplicationUtils.mailPassword = mailPassword;
	}

	public static String getMailAuth() {
		return mailAuth;
	}

	@Value("${spring.mail.properties.mail.smtp.auth}")
	public void setMailAuth(String mailAuth) {
		ApplicationUtils.mailAuth = mailAuth;
	}

	public Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());

	}

}
