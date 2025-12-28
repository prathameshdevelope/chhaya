package com.test.test.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.test.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

	Optional<Property> findByOwner_Id(Long userId);

	@Query("SELECT p FROM Property p WHERE p.isActive = true")
    List<Property> findAllActive();
}
