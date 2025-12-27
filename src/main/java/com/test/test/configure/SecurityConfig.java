package com.test.test.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) 
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/chhaya/user/signup","/chhaya/user/login","/chhaya/user/send-otp","/chhaya/user/verify-otp","/chhaya/user/user/{id}","/test").permitAll() 
	                .anyRequest().authenticated()
	            )
	            .formLogin(form -> form.disable())
	            .httpBasic(httpBasic -> httpBasic.disable());

	        return http.build();
	    }
	 
	 @Bean
	 public UserDetailsService users() {
	 	UserDetails user = User.builder()
	 		.username("prathamesh.g9767@gmail.com")
	 		.password("{bcrypt}$2a$10$whrOw52QD406He8yPV.1neap4V3tko.FSBrfLH.65llTBNQAY0GdW")
	 		.roles("USER")
	 		.build();
	 	UserDetails admin = User.builder()
	 		.username("admin")
	 		.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
	 		.roles("USER", "ADMIN")
	 		.build();
	 	return new InMemoryUserDetailsManager(user, admin);
	 }
}