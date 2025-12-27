package com.test.test.entity;

import java.time.LocalDateTime;

import com.test.test.entity.enums.Gender;
import com.test.test.entity.enums.Role;
import com.test.test.entity.enums.VerificationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    @NotBlank(message = "Name is required")
	    private String name;

	    @Column(nullable = false, unique = true, length = 100)
	    @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email")
	    private String email;

	    @Column(name = "mobile_number",unique = true, nullable = false, length = 20)
	    @NotBlank(message = "Mobile Number number is required")
	    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	    private String mobileNumber;

	    @Column(nullable = false, length = 255)
	    @NotBlank(message = "Password is required")
	    private String password;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Role role;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = true)
	    private Gender gender;

	    private Integer otp;

	    @Column(name = "otp_expiry")
	    private LocalDateTime otpExpiry;
	    
	    @Column(columnDefinition = "TEXT")
	    private String address;

	    @Column(name = "is_verified")
	    private Boolean isVerified = false;

	    @Column(name = "is_active")
	    private Boolean isActive = true;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "verification_status")
	    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

	    @Column(name = "verified_at")
	    private LocalDateTime verifiedAt;

	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt;
	    
	    @PrePersist
	    protected void onCreate() { 
	        this.createdAt = LocalDateTime.now();

	        if (this.role != Role.OWNER) {
	            this.isVerified = true;
	            this.verificationStatus = VerificationStatus.APPROVED;
	        }
	    }
}
