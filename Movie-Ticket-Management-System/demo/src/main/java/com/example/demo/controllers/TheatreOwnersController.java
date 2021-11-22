package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CustomExceptions.ResourceNotFoundException;
import com.example.demo.model.TheatreOwners;
import com.example.demo.repository.TheatreOwnersRepository;

@RestController
@RequestMapping("/api/")
public class TheatreOwnersController {
	
	@Autowired
	private TheatreOwnersRepository repo;
	
	//create theatre
	@PostMapping("/addTheatre")
	public TheatreOwners createTheatre(@RequestBody TheatreOwners the) {
		return repo.save(the);	
	}
	
	//getTheatres
	@GetMapping("/theatres")
	public List<TheatreOwners> getAllThatres(){
		return repo.findAll();
	}
	
	//getTheatreById
	@GetMapping("/theatre/{id}")
	public ResponseEntity<TheatreOwners> getTheatreById(@PathVariable Long id) throws ResourceNotFoundException{
		TheatreOwners th=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Theatre not exit with this id"+id));
	return ResponseEntity.ok(th);
	}
	
	//updateTheatreById
	@PutMapping("/theatre/{id}")
	public ResponseEntity<TheatreOwners> updateThetreById(@PathVariable Long id,TheatreOwners the) throws ResourceNotFoundException{
		TheatreOwners th=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Theatre not exit with this id"+id ));
		th.setFirstName(the.getFirstName());
		th.setLastName(the.getLastName());
		th.setPassword(the.getPassword());
		th.setEmail(the.getEmail());
		th.setAddress(the.getAddress());
		TheatreOwners updateTheatres=repo.save(th);
		return ResponseEntity.ok(updateTheatres);
	}
	
	//deleteTheatreById
	@DeleteMapping("/theatre/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteTheatre(@PathVariable Long id) throws ResourceNotFoundException{
		TheatreOwners th=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Theatre not exit with this id"+id));
		repo.delete(th);
		Map<String,Boolean> response=new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	

}
