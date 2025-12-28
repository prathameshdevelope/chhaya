package com.test.test.service;

import java.util.List;

import com.test.test.dto.SavedPropertyResponse;

public interface ISavedPropertyService {

	boolean toggleSaveProperty(Long userId, Long propertyId);

	List<SavedPropertyResponse> getSavedPropertiesByUser(Long userId);

	void unsaveProperty(Long userId, Long propertyId);

}
