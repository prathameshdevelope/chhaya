package com.test.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.test.entity.Property;

public interface PropertyRepo extends JpaRepository<Property, Long> {

}
