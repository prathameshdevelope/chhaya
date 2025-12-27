package com.test.test.dto;

import java.util.List;

import com.test.test.entity.enums.OccupancyType;
import com.test.test.entity.enums.UnitType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePropertyRequest {
	@NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Unit type is required")
    private UnitType unitType;

    @NotBlank(message = "Occupancy type is required")
    private OccupancyType occupancyType;

    private String description;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City cannot exceed 50 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State cannot exceed 50 characters")
    private String state;

    @Size(max = 10, message = "Zip code cannot exceed 10 characters")
    private String zipCode;

    private List<String> amenities; 

    private String rules;
}
