package com.test.test.dto;

import java.time.LocalDateTime;

import com.test.test.entity.enums.Gender;
import com.test.test.entity.enums.Role;
import com.test.test.entity.enums.VerificationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

	    private String name;
	    private String email;
	    private String mobileNumber;
	    private Role role;
	    private Gender gender;
	    private String address;
	    private VerificationStatus verificationStatus;
	    private LocalDateTime createdAt;
}
