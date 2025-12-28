package com.test.test.configure;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.test.test.repo.UsersRepo;

public class DataLoader implements ApplicationRunner{

	private UsersRepo userRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
        		System.out.println("started");
	}
	


}
