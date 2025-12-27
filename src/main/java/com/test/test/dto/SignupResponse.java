package com.test.test.dto;

import com.test.test.entity.enums.Role;
import com.test.test.entity.enums.VerificationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {
	  private String message;
	    private Long userId;
	    private Role role;
	    private VerificationStatus status;
	    private String token;
}
