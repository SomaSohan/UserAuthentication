package com.example.demo.controller;

import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.UserOtp;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;

@Controller
public class UserController
{
	@Autowired
	UserService userService;

	@GetMapping("/index")
	public String indexPage()
	{
		return "index";
	}
	
	@GetMapping("/login")
	public String goToLogin()
	{
		return "login";
	}
	
	@PostMapping("/signupdata")
	public String signUp(@RequestParam String name, @RequestParam String number, @RequestParam String email, @RequestParam String password)
	{
	    
		userService.insertData(name, number, email, password);
		return "signupsuccess";
	}
	
	
	@PostMapping("/logindata")
	public String loginData(@RequestParam String email, @RequestParam String password)
	{
		Users user  = userService.loginData(email, password);
		if(user == null)
		{
			return "loginFail";
		}
		String useremail = user.getEmail();
		String userPassword = user.getPassword();
		
		
		
		
		if(useremail.equals(email) && userPassword.equals(password))
		{
			Random random = new Random();
			int otp1 = random.nextInt(100000, 1000000);
			String otp2 = String.valueOf(otp1);
			
			int userId = user.getId();
			userService.insertUserOtp(otp2, userId);
			
			userService.sendOtpEmail(useremail, otp2);
			
			return "loginSuccess";
		}
		
		
		return "loginFail";
		
	}
	
	@PostMapping("/verifyotp")
	public String verifyUser(String otp)
	{
		boolean ans = userService.verifyUser(otp);
		if(ans)
		{
			return "home";
		}
		return "loginFail";
	}
}
