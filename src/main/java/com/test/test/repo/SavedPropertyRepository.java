package com.test.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.test.entity.SavedProperty;

@Repository
public interface SavedPropertyRepository extends JpaRepository<SavedProperty, Long>{

}
