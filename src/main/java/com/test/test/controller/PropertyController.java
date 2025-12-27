package com.test.test.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.dto.CreatePropertyRequest;
import com.test.test.dto.PropertyResponse;
import com.test.test.dto.UpdatePropertyRequest;
import com.test.test.service.IPropertyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/chhaya/property")
public class PropertyController {
	
	   private IPropertyService propertyService;
	   
	   public PropertyController(IPropertyService iPropertyService) {
		   this.propertyService=iPropertyService;
	   }
	   
	    @PostMapping("/create")
	    public ResponseEntity<PropertyResponse> createProperty(
	            @RequestParam Long ownerId,   // later from JWT
	            @RequestBody @Valid CreatePropertyRequest request
	    ) {
	        return ResponseEntity.ok(
	                propertyService.createProperty(ownerId, request)
	        );
	    }

	    @GetMapping("/get/{id}")
	    public ResponseEntity<PropertyResponse> getProperty(@PathVariable Long id) {
	        return ResponseEntity.ok(propertyService.getPropertyById(id));
	    }

	    @GetMapping("/all/active")
	    public ResponseEntity<List<PropertyResponse>> getAllProperties() {
	        return ResponseEntity.ok(propertyService.getAllActiveProperties());
	    }
	    
	    @GetMapping("/get/{userid}")
	    public ResponseEntity<PropertyResponse> getAllPropertyUserId(@PathVariable Long userid) {
	        return ResponseEntity.ok(propertyService.getAllPropertyUserId(userid));
	    }

	    @PutMapping("/update/{id}")
	    public ResponseEntity<PropertyResponse> updateProperty(
	            @PathVariable Long id,
	            @RequestParam Long ownerId,
	            @RequestBody @Valid UpdatePropertyRequest request
	    ) {
	        return ResponseEntity.ok(
	                propertyService.updateProperty(id, ownerId, request)
	        );
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteProperty(
	            @PathVariable Long id,
	            @RequestParam Long ownerId
	    ) {
	        propertyService.deleteProperty(id, ownerId);
	        return ResponseEntity.ok("Property deleted successfully");
	    }


}