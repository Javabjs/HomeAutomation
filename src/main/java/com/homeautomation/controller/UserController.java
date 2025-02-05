package com.homeautomation.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeautomation.model.User;
import com.homeautomation.service.UserService;

@RestController
@RequestMapping("/user")

public class UserController {
	@Autowired
	private UserService uService;
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return uService.createUser(user) ;
	
	}
	
	@GetMapping("/{uuid}")
	public Optional<User> getUserByUuid(@PathVariable String uuid){
		return uService.getUserByUuid(UUID.fromString(uuid));
		
	}
	
	@GetMapping
	public List<User> getAllUser(){
		return uService.getAllUser();
		
	}
	
	@DeleteMapping("/{uuid}")
	public String deleteByUuid(@PathVariable String uuid) {
		return uService.deleteByUuid(UUID.fromString(uuid));
		
	}
	
	@PutMapping("/{uuid}")
	public User updateByUuid(@PathVariable String uuid,@RequestBody User user) {
		return uService.updateByUuid(UUID.fromString(uuid), user);
		
	}

}
