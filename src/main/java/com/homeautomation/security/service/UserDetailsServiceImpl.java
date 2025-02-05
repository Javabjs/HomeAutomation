package com.homeautomation.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.homeautomation.model.User;
import com.homeautomation.repository.UserRepository;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {
	  @Autowired
	    private UserRepository userRepository;
	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	            Optional<User> userDetails = userRepository.findByEmail(email);
	            User user=userDetails.get();
	            return  UserDetailsImpl.build(user);

	    }

}
