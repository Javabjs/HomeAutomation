package com.homeautomation.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.homeautomation.model.User;
import com.homeautomation.repository.UserRepository;



@Service

public class UserServiceImpl  implements UserService{
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository uRepository;
	
	@Override
	public User createUser(User user) {
		user.setPassword(encoder.encode( user.getPassword()));
		return uRepository.save(user);
	 }

	@Override
	public Optional<User> getUserByUuid(UUID uuid ){
		return uRepository.findById(uuid);
		
	}
	
	@Override
	public List<User> getAllUser(){
		return uRepository.findAll();
	}
	
	@Override
	public String deleteByUuid(UUID uuid) {
		uRepository.deleteById(uuid);
		return "Id Deleted Successfully";
	}
	
	@Override
	public User updateByUuid(UUID uuid, User user) {
		Optional<User>usr=uRepository.findById(uuid);
		User u=usr.get();
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setPhone_no(user.getPhone_no());
		u.setComplete_address(user.getComplete_address());
		u.setDistrict(user.getDistrict());
		u.setState(user.getState());
		u.setCountry(user.getCountry());
		u.setPincode(user.getPincode());
		u.setUpdatedAt(user.getUpdatedAt());
		return uRepository.save(u);
		
		
		
		
	}

}
