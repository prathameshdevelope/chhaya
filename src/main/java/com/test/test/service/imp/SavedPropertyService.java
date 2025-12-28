package com.test.test.service.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.test.dto.SavedPropertyResponse;
import com.test.test.entity.Property;
import com.test.test.entity.SavedProperty;
import com.test.test.entity.Users;
import com.test.test.entity.enums.Role;
import com.test.test.repo.PropertyRepo;
import com.test.test.repo.SavedPropertyRepository;
import com.test.test.repo.UsersRepo;
import com.test.test.service.ISavedPropertyService;

@Service
public class SavedPropertyService implements ISavedPropertyService{
	   
	     private SavedPropertyRepository savePropertyRepo;
	     private UsersRepo userRepo;
	     private PropertyRepo propertyRepo;

    	 @Override
	    public boolean toggleSaveProperty(Long userId, Long propertyId) {
	        Users user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

	        if (user.getRole() != Role.VISITOR && user.getRole() != Role.OWNER) {
	            throw new RuntimeException("Only visitors or owners can save properties");
	        }
	        Property property = propertyRepo.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found"));

	        Optional<SavedProperty> existing = savePropertyRepo.findByUserIdAndPropertyId(userId, propertyId);

	        if (existing.isPresent()) {
	            savePropertyRepo.deleteByUserIdAndPropertyId(userId, propertyId);
	            return false;
	        } else {
	            SavedProperty saved = new SavedProperty();
	            saved.setUser(user);
	            saved.setProperty(property);
	            saved.setSavedAt(LocalDateTime.now());
	            savePropertyRepo.save(saved);
	            return true;
	        }
	    }
    	 
    	 @Override
    	 public List<SavedPropertyResponse> getSavedPropertiesByUser(Long userId) {
    		 List<SavedProperty> savedProperties =
    		            savePropertyRepo.findAllByUserId(userId);

    		    List<SavedPropertyResponse> responseList = new ArrayList<>();

    		    for (SavedProperty sp : savedProperties) {
    		        SavedPropertyResponse response = new SavedPropertyResponse();
    		        response.setId(sp.getId());
    		        response.setPropertyId(sp.getProperty().getId());
    		        response.setPropertyTitle(sp.getProperty().getTitle());
    		        response.setSavedAt(sp.getSavedAt());
    		        responseList.add(response);
    		    }
    		    return responseList;
    	    }

    	 @Override
    	 public void unsaveProperty(Long userId, Long propertyId) {
    		 SavedProperty saved = savePropertyRepo.findByUserIdAndPropertyId(userId, propertyId)
    				 .orElseThrow(() -> new RuntimeException("Property not saved by this user"));

    		 savePropertyRepo.delete(saved);			

    	 }
}
