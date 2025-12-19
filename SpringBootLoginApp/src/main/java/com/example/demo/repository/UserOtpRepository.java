package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserOtp;

@Repository
public interface UserOtpRepository extends JpaRepository<UserOtp, Integer>
{

	UserOtp findByOtp(String otp);
}
