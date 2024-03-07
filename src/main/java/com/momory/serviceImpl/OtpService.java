package com.momory.serviceImpl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {


	private static Map<String, String> otpMap = new HashMap<>();

	@Autowired
	private EmailService emailService;


//    public String generateOtp(String phoneNo){
//        PhoneNumber to = new PhoneNumber(phoneNo);
//        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
//        String otp = getRandomOTP(phoneNo);
//        String otpMessage = "Dear Customer , Your OTP is " + otp + ". Use this otp to log in to Rapido Clone Application";
//        Message message = Message
//                .creator(to, from,
//                        otpMessage)
//                .create();
//        return  otp;
//    }

	public String generateOtp(String email) {
		String otp = getRandomOTP(email);
		String otpMessage = "Dear Customer , Your OTP is " + otp + ". Use this otp to log in to Memory Meets Infinity";

		emailService.sendOtp(email, otpMessage);

		return otp;
	}

	private String getRandomOTP(String email) {
		String otp = new DecimalFormat("000000").format(new Random().nextInt(999999));
		otpMap.put(email, otp);
		return otp;
	}

	public String getCacheOtp(String key) {
		try {
			
			  for (String email : otpMap.keySet()) {
		            String otp = otpMap.get(email);
		            System.out.println("Email: " + email + ", OTP: " + otp);
		        }

			return otpMap.get(key);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public void clearOtp(String key) {
		otpMap.remove(key);
	}
}