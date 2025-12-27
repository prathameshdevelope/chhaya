package com.test.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.dto.PropertiesResponse;
import com.test.test.service.IVisitorService;


@RestController
@RequestMapping("/chhaya/vistor")
public class VisitorController {
	
	private final IVisitorService iVistorServer;
	
	public VisitorController(IVisitorService iVistorServer) {
		this.iVistorServer=iVistorServer;
	}
	
	@GetMapping("/home/trending/properties")
	public PropertiesResponse getTrendingProperTies( ) {
		
		return null;
	}
	

}