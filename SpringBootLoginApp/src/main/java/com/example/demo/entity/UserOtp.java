package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserOtp 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int otp_id;
	
	@Column
	String otp;
	
	@Column
	int user_id;

	public UserOtp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserOtp(String otp, int user_id) {
		super();
		this.otp = otp;
		this.user_id = user_id;
	}

	public int getOtp_id() {
		return otp_id;
	}

	public void setOtp_id(int otp_id) {
		this.otp_id = otp_id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
}
