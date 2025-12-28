package com.test.test.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.test.entity.SavedProperty;

@Repository
public interface SavedPropertyRepository extends JpaRepository<SavedProperty, Long>{

    Optional<SavedProperty> findByUserIdAndPropertyId(Long userId, Long propertyId);
    
    @Query("SELECT sp FROM SavedProperty sp " +
            "JOIN FETCH sp.property p " +
            "WHERE sp.user.id = :userId " +
            "ORDER BY sp.savedAt DESC")
     List<SavedProperty> findAllByUserId(@Param("userId") Long userId);
    
    @Modifying
    @Query("DELETE FROM SavedProperty sp WHERE sp.user.id = :userId AND sp.property.id = :propertyId")
    void deleteByUserIdAndPropertyId(@Param("userId") Long userId, @Param("propertyId") Long propertyId);

}
