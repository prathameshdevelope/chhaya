package com.test.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.test.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long>{
	Users findAllByMobileNumber(String mobileNumber);

	Users findByEmail(String email);

}
