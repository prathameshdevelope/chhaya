package com.test.test.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.dto.PropertyResponse;
import com.test.test.dto.SavedPropertyResponse;
import com.test.test.service.IPropertyService;
import com.test.test.service.ISavedPropertyService;


@RestController
@RequestMapping("/api/chhaya/vistor")
public class VisitorController {
	
	private final ISavedPropertyService savedPropertyService;
	private final IPropertyService propertyService;
	
	public VisitorController(ISavedPropertyService savedPropertyService,IPropertyService propertyService) {
		this.savedPropertyService=savedPropertyService;
		this.propertyService=propertyService;
	}
	
	@GetMapping("/all/active")
    public ResponseEntity<List<PropertyResponse>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllActiveProperties());
    }
	
	 @GetMapping("/my-saved")
	    public ResponseEntity<List<SavedPropertyResponse>> getMySavedProperties(
	    		@PathVariable Long userId) {

	        List<SavedPropertyResponse> saved = savedPropertyService.getSavedPropertiesByUser(userId);
	        return ResponseEntity.ok(saved);
	 }
	

}