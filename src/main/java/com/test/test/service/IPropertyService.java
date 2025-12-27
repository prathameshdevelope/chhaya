package com.test.test.service;

import java.util.List;

import com.test.test.dto.CreatePropertyRequest;
import com.test.test.dto.PropertyResponse;
import com.test.test.dto.UpdatePropertyRequest;

public interface IPropertyService {
	
	PropertyResponse createProperty(Long ownerId, CreatePropertyRequest request);
	PropertyResponse getPropertyById(Long propertyId);
	List<PropertyResponse> getAllActiveProperties();
	PropertyResponse updateProperty(Long propertyId, Long ownerId, UpdatePropertyRequest request);
	void deleteProperty(Long propertyId, Long ownerId);
	PropertyResponse getAllPropertyUserId(Long userid);

}