package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Vehicule;
import com.example.demo.repository.VehiculeRepository;

//origins = "http://localhost:4200"
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/")
public class vehiculeController {
	@Autowired
	private VehiculeRepository vehiculeRepository;
	
	@GetMapping("/vehicules")
	public List<Vehicule> getAllVehicule(){
		return vehiculeRepository.findAll();
	}
	
	@GetMapping("/vehicule/{id}")
	public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id) {
		Vehicule vehicule = vehiculeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("vehicule not exist with id :" + id));
		return ResponseEntity.ok(vehicule);
				
		
	}

}
