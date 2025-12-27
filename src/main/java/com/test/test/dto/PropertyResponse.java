package com.test.test.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.test.test.entity.enums.OccupancyType;
import com.test.test.entity.enums.UnitType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponse {

	private Long id;
	private String ownerName;
	private String title;
	private UnitType unitType;
	private OccupancyType occupancyType;
	private String description;
	private Double rating;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private List<String> amenities;
	private Boolean isActive;
	private String rules;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
