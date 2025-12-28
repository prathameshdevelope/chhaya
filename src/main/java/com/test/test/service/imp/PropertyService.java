package com.test.test.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.test.test.dto.CreatePropertyRequest;
import com.test.test.dto.PropertyResponse;
import com.test.test.dto.UpdatePropertyRequest;
import com.test.test.entity.Property;
import com.test.test.entity.Users;
import com.test.test.entity.enums.Role;
import com.test.test.repo.PropertyRepository;
import com.test.test.repo.UsersRepo;
import com.test.test.service.IPropertyService;

@Service
public class PropertyService implements IPropertyService{

	private final PropertyRepository propertyRepo;
	private final UsersRepo userRepo;

	public PropertyService(PropertyRepository propertyRepo,UsersRepo userRepo) {
		this.propertyRepo = propertyRepo;
		this.userRepo = userRepo;
	}

	@Override
	public PropertyResponse createProperty(Long ownerId, CreatePropertyRequest request) {
		Users owner = userRepo.findById(ownerId)
				.orElseThrow(() -> new RuntimeException("User not found"));

		checkVerifiedOwner(owner.getRole(),owner.getIsVerified(),owner.getIsActive());
		
		Property property = new Property();
		property.setOwner(owner);
		property.setTitle(request.getTitle());
		property.setUnitType(request.getUnitType());
		property.setOccupancyType(request.getOccupancyType());
		property.setDescription(request.getDescription());
		property.setAddress(request.getAddress());
		property.setCity(request.getCity());
		property.setState(request.getState());
		property.setZipCode(request.getZipCode());
		property.setAmenities(request.getAmenities());
		property.setRules(request.getRules());

		propertyRepo.save(property);
		return mapToResponse(property);
	}

	@Override
	public PropertyResponse getPropertyById(Long propertyId) {
       Property property=propertyRepo.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found"));
       checkPropertyStatus(property.getIsActive());
		return mapToResponse(
				propertyRepo.findById(propertyId)
				.orElseThrow(() -> new RuntimeException("Property not found"))
				);
	}

	@Override
	public List<PropertyResponse> getAllActiveProperties() {
		return propertyRepo.findAllActive()
				.stream()
				.map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public PropertyResponse getAllPropertyUserId(Long userId) {
		Users owner = userRepo.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));

		checkVerifiedOwner(owner.getRole(),owner.getIsVerified(),owner.getIsActive());
		return mapToResponse(
				propertyRepo.findByOwner_Id(userId)
				.orElseThrow(() -> new RuntimeException("Property not found"))
				);
	}

	@Override
	public PropertyResponse updateProperty(Long propertyId, Long ownerId, UpdatePropertyRequest request) {
		Property property = propertyRepo.findById(propertyId)
				.orElseThrow(() -> new RuntimeException("Property not found"));
		Users owner = userRepo.findById(ownerId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		checkVerifiedOwner(owner.getRole(),owner.getIsVerified(),owner.getIsActive());
		
		if (!property.getOwner().getId().equals(ownerId)) {
			throw new RuntimeException("You are not allowed to update this property");
		}

		if (request.getTitle() != null)
			property.setTitle(request.getTitle());

		if (request.getUnitType() != null)
			property.setUnitType(request.getUnitType());

		if (request.getOccupancyType() != null)
			property.setOccupancyType(request.getOccupancyType());

		if (request.getDescription() != null)
			property.setDescription(request.getDescription());

		if (request.getAddress() != null)
			property.setAddress(request.getAddress());

		if (request.getCity() != null)
			property.setCity(request.getCity());

		if (request.getState() != null)
			property.setState(request.getState());

		if (request.getZipCode() != null)
			property.setZipCode(request.getZipCode());

		if (request.getAmenities() != null)
			property.setAmenities(request.getAmenities());

		if (request.getRules() != null)
			property.setRules(request.getRules());
		property.setUpdatedAt(LocalDateTime.now());
		propertyRepo.save(property);
		return mapToResponse(property);
	}

	@Override
	public void deleteProperty(Long propertyId, Long ownerId) {
		Property property = propertyRepo.findById(propertyId)
				.orElseThrow(() -> new RuntimeException("Property not found"));
		Users owner = userRepo.findById(ownerId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		checkVerifiedOwner(owner.getRole(),owner.getIsVerified(),owner.getIsActive());
		
		if (!property.getOwner().getId().equals(ownerId)) {
			throw new RuntimeException("You are not allowed to delete this property");
		}

		propertyRepo.delete(property);
	}

	private PropertyResponse mapToResponse(Property property) {
		
	    Optional<Users> user=userRepo.findById(property.getOwner().getId());
		PropertyResponse response = new PropertyResponse();
		response.setId(property.getId());
		response.setOwnerName(user.get().getName());
		response.setTitle(property.getTitle());
		response.setUnitType(property.getUnitType());
		response.setOccupancyType(property.getOccupancyType());
		response.setDescription(property.getDescription());
		response.setRating(property.getRating());
		response.setAddress(property.getAddress());
		response.setCity(property.getCity());
		response.setState(property.getState());
		response.setZipCode(property.getZipCode());
		response.setAmenities(property.getAmenities());
		response.setIsActive(property.getIsActive());
		response.setRules(property.getRules());
		response.setCreatedAt(property.getCreatedAt());
		response.setUpdatedAt(property.getUpdatedAt());
		return response;
	}
	
	public void checkVerifiedOwner(Role role,Boolean isVerified,Boolean isActive) {
		if (role != Role.OWNER) 
			throw new RuntimeException("Only OWNER can create property");
		
		if (!isVerified) 
			throw new RuntimeException("Owner not approved by admin");
		
		if(!isActive)
			throw new RuntimeException("User is Disabled,Please Contact Admin");
	}
	
	public void checkPropertyStatus(Boolean isActive) {
		if(!isActive)
			throw new RuntimeException("Property is Disabled,Please make sure Property is active");
			
	}

}