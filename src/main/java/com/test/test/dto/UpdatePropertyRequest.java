package com.test.test.dto;

import java.util.List;

import com.test.test.entity.enums.OccupancyType;
import com.test.test.entity.enums.UnitType;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePropertyRequest {
	@Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    private UnitType unitType;

    private OccupancyType occupancyType;

    private String description;

    private String address;

    @Size(max = 50, message = "City cannot exceed 50 characters")
    private String city;

    @Size(max = 50, message = "State cannot exceed 50 characters")
    private String state;

    @Size(max = 10, message = "Zip code cannot exceed 10 characters")
    private String zipCode;

    private List<String> amenities;

    private String rules;
}
