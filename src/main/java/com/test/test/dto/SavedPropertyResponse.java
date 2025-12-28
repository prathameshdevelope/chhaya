package com.test.test.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedPropertyResponse {
	  private Long id;
	    private Long propertyId;
	    private String propertyTitle;
	    private LocalDateTime savedAt;
}
