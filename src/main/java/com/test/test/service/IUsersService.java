package com.test.test.service;

import com.test.test.dto.LoginRequest;
import com.test.test.dto.LoginResponse;
import com.test.test.dto.ProfileResponse;
import com.test.test.dto.ResponseDto;
import com.test.test.dto.SignupRequest;
import com.test.test.dto.SignupResponse;
import com.test.test.dto.sendOtpDto;
import com.test.test.dto.verifyOtpDto;

public interface IUsersService {
	SignupResponse signUp(SignupRequest requestBody);

	LoginResponse login(LoginRequest requestBody);

	ResponseDto sendOtp(sendOtpDto requestBody);

	ResponseDto verifyOtp(verifyOtpDto requestBody);

	ProfileResponse getData(Long id);

}
