package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserOtp;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserOtpRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	UserRepository userRep;
	
	@Autowired
	UserOtpRepository userOtpRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	// Login - find user by email
	public Users loginData(String email, String password)
	{
		return userRep.findByEmail(email);
	}
	
	
	// Register new user
	public void insertData(String name, String number, String email, String password)
	{
		Users user = new Users();
		user.setName(name);
		user.setNumber(number);
		user.setEmail(email);
		user.setPassword(password);
		
		userRep.save(user);
	}
	
	
	
	public void insertUserOtp(String otp1, int id)
	{
		UserOtp userOtp = new UserOtp();
		userOtp.setOtp(otp1);
		userOtp.setUser_id(id);
		userOtpRepo.save(userOtp);
	}
	
	
	
	public boolean verifyUser(String otp)
	{
		UserOtp otpObj = userOtpRepo.findByOtp(otp);
		return otpObj != null;
	}
	
	
	
	public void sendOtpEmail(String toEmail, String otp)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("Your OTP Verification Code");
		message.setText("Your login OTP is: " + otp);

		mailSender.send(message);
	}
}
