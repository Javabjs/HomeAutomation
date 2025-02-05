package com.homeautomation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeautomation.model.JwtRequest;
import com.homeautomation.model.JwtResponse;
import com.homeautomation.security.jwtutils.JwtHelper;
import com.homeautomation.security.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")


public class AuthController {
	
	 @Autowired
	    AuthenticationManager authenticationManager;
	    @Autowired
	    private UserDetailsServiceImpl userDetailsService;
	    @PostMapping("/login")
	    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
	    	
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String token = JwtHelper.generateJwtToken(authentication);
	        JwtResponse response=new JwtResponse();
	        response.setJwtToken(token);
	        response.setEmail(request.getEmail());

	        return new ResponseEntity<>(response, HttpStatus.OK);

	    }

	}


