package com.test.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.dto.LoginRequest;
import com.test.test.dto.LoginResponse;
import com.test.test.dto.ProfileResponse;
import com.test.test.dto.ResponseDto;
import com.test.test.dto.SignupRequest;
import com.test.test.dto.SignupResponse;
import com.test.test.dto.sendOtpDto;
import com.test.test.dto.verifyOtpDto;
import com.test.test.service.IUsersService;

@RestController
@RequestMapping("/api/chhaya/user")
public class UsersController {
	
	private final IUsersService appService;
	
	public UsersController(IUsersService service1) {
		this.appService=service1;
	}

	@PostMapping("/signup")
	public ResponseEntity<SignupResponse>signUp(@Validated @RequestBody SignupRequest requestBody) {
		SignupResponse signupResponse = appService.signUp(requestBody);
		 return ResponseEntity.ok(signupResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest requestBody) {
		LoginResponse responseDto=appService.login(requestBody);
		return ResponseEntity.ok(responseDto);
	}
	
	@PostMapping("/send-otp")
	public ResponseEntity<ResponseDto> sendOtp(@RequestBody sendOtpDto requestBody) {
		ResponseDto responseDto=appService.sendOtp(requestBody);
		return ResponseEntity.ok(responseDto);
	}
	
	@PostMapping("/verify-otp")
	public ResponseEntity<ResponseDto> verifyOtp(@RequestBody verifyOtpDto requestBody) {
		ResponseDto responseDto=appService.verifyOtp(requestBody);
		return ResponseEntity.ok(responseDto);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<ProfileResponse> getUserData(@PathVariable Long id) {
		ProfileResponse responseDto=appService.getData(id);
		return ResponseEntity.ok(responseDto);
	} 
}
