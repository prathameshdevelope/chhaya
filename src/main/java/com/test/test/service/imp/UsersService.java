package com.test.test.service.imp;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.test.configure.OtpUtil;
import com.test.test.dto.LoginRequest;
import com.test.test.dto.LoginResponse;
import com.test.test.dto.ProfileResponse;
import com.test.test.dto.ResponseDto;
import com.test.test.dto.SignupRequest;
import com.test.test.dto.SignupResponse;
import com.test.test.dto.sendOtpDto;
import com.test.test.dto.verifyOtpDto;
import com.test.test.entity.Users;
import com.test.test.entity.enums.Role;
import com.test.test.entity.enums.VerificationStatus;
import com.test.test.repo.UsersRepo;
import com.test.test.service.IUsersService;


@Service
public class UsersService implements IUsersService{


	private final PasswordEncoder passwordEncoder;
	private final UsersRepo userRepo;
	private final JavaMailSender mailSender;
	
	@Value("${otp.expiry.minutes}")
	private int otpExpiryTime;

	public UsersService(PasswordEncoder pass,UsersRepo repo,JavaMailSender mailSender) {
		this.userRepo=repo;
		this.passwordEncoder=pass;
		this.mailSender=mailSender;
	}


	@Override
	public SignupResponse signUp(SignupRequest requestBody) {
		Users storedData=userRepo.findAllByMobileNumber(requestBody.getMobileNumber());
		if(storedData !=null) {
			return new SignupResponse("User already exists", storedData.getId(),storedData.getRole(),storedData.getVerificationStatus(),null);
		}
		
		Users storedData1=userRepo.findByEmail(requestBody.getEmail());
		if(storedData1 !=null) {
			return new SignupResponse("User already exists", storedData1.getId(),storedData1.getRole(),storedData1.getVerificationStatus(),null);
		}
		Users newUser = new Users();
		newUser.setName(requestBody.getName());
		newUser.setEmail(requestBody.getEmail());
		newUser.setMobileNumber(requestBody.getMobileNumber());
		newUser.setPassword(passwordEncoder.encode(requestBody.getPassword()));
		newUser.setCreatedAt(LocalDateTime.now());
		newUser.setIsActive(false);
		newUser.setRole(requestBody.getRole());
		if(requestBody.getRole().equals(Role.VISITOR)) {
		newUser.setGender(requestBody.getGender());
		}
		if(requestBody.getRole().equals(Role.OWNER)) {
			newUser.setAddress(requestBody.getAddress());
			newUser.setVerificationStatus(VerificationStatus.PENDING);
			newUser.setIsVerified(false);
		}
		Users registered=userRepo.save(newUser);
		return new SignupResponse("User Registered Successfully",registered.getId(),registered.getRole(),registered.getVerificationStatus(),null);	
}


	@Override
	public LoginResponse login(LoginRequest requestBody) {
		Users storedData=userRepo.findAllByMobileNumber(requestBody.getMobileNumber());
		if(storedData ==null) {
			return new LoginResponse("Customer Not Found", "404");
		}
		if(passwordEncoder.matches(requestBody.getPassword(),storedData.getPassword())) {
			return new LoginResponse("Password verified successfully,Mail id-"+storedData.getEmail(), "200");
		}
		return new LoginResponse("Invalid password", "401");
	}


	@Override
	public ResponseDto sendOtp(sendOtpDto requestBody) {
		Users storedData=userRepo.findByEmail(requestBody.getEmail());
		if(storedData == null) {
			return new ResponseDto("Email id not matching with any User", "404");
		}
          
		Integer otp = OtpUtil.generateOtp();
		storedData.setOtp(otp);
		storedData.setOtpExpiry(LocalDateTime.now().plusMinutes(otpExpiryTime));
		userRepo.save(storedData);

//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(storedData.getEmail());
//		message.setSubject("Your OTP for Verification");
//		message.setText("Your OTP is: " + otp + ". It expires in"+otpExpiryTime+"minutes.");		
//		try {
//			mailSender.send(message);
//		} catch (Exception e) {
//			return new ResponseDto("Failed to send OTP: " + e.getMessage(), "500");
//		}
		return new ResponseDto("OTP sent successfully", "200");
	}


	@Override
	public ResponseDto verifyOtp(verifyOtpDto requestBody) {
		Users storedData=userRepo.findByEmail(requestBody.getEmail());
		if(storedData== null) {
			return new ResponseDto("Email id not matching with any User", "404");
		}

		if (storedData.getOtpExpiry() == null || storedData.getOtpExpiry().isBefore(LocalDateTime.now())) {
			return new ResponseDto("OTP has expired", "400");
		}
		int otp=storedData.getOtp();
		int requestOtp= requestBody.getOtp();
		if(otp==requestOtp) {
			storedData.setOtp(null);
			storedData.setOtpExpiry(null);
			storedData.setIsActive(true);
			userRepo.save(storedData);
			return new ResponseDto("OTP verified successfully", "200");
		}		
		return new ResponseDto("Invalid OTP", "401");	
	}

	@Override
	public ProfileResponse getData(Long id) {
		Users storedData=userRepo.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
		ProfileResponse response=new ProfileResponse();
		response.setName(storedData.getName());
		response.setAddress(storedData.getAddress());
		response.setEmail(storedData.getEmail());
		response.setMobileNumber(storedData.getMobileNumber());
		response.setGender(storedData.getGender());
		response.setVerificationStatus(storedData.getVerificationStatus());
		response.setCreatedAt(storedData.getCreatedAt());
		response.setRole(storedData.getRole());
		return response;
	}
}
