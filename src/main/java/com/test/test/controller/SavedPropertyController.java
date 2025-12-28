package com.test.test.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.dto.SavedPropertyResponse;
import com.test.test.service.ISavedPropertyService;

@RestController
@RequestMapping("/api/chhaya/saved/property")
public class SavedPropertyController {

	private final ISavedPropertyService savedPropertyService;

    public SavedPropertyController(ISavedPropertyService savedPropertyService) {
    	this.savedPropertyService=savedPropertyService;
    }
    
    @PostMapping("/save/{propertyId}/{userId}")
    public ResponseEntity<?> toggleSaveProperty(
    		@PathVariable Long userId,
            @PathVariable Long propertyId) {
    	
        boolean isSaved = savedPropertyService.toggleSaveProperty(userId,propertyId);

        if (isSaved) {
            return ResponseEntity.ok("Property saved successfully");
        } else {
            return ResponseEntity.ok("Property unsaved successfully");
        }
    }

    @GetMapping("/my-saved")
    public ResponseEntity<List<SavedPropertyResponse>> getMySavedProperties(
    		@PathVariable Long userId) {

        List<SavedPropertyResponse> saved = savedPropertyService.getSavedPropertiesByUser(userId);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/unsave/{propertyId}/{userId}")
    public ResponseEntity<String> unsaveProperty(
            @PathVariable Long propertyId,
            @PathVariable Long userId) {
         savedPropertyService.unsaveProperty(userId,propertyId);
      
        return ResponseEntity.noContent().build();
    }
}
