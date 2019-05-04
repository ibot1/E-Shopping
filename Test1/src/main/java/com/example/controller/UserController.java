package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.security.MapValidation;
import com.example.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userService; 
	
	@Autowired
	MapValidation mapValidation;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody User user, BindingResult result) {
		ResponseEntity<?> errFieldLists = mapValidation.mapValidationErrors(result);
		if(errFieldLists != null)	return errFieldLists;
		
		String email = user.getEmail();
		if(userService.registerUser(email, user.getPassword()) != true)	return new ResponseEntity<String>("Email '" + email + "' already registered!", HttpStatus.OK);
		return new ResponseEntity<String>("Successfully, registered Email-id '" + email + "' !", HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody User user, BindingResult result){
		ResponseEntity<?> errFieldLists = mapValidation.mapValidationErrors(result);
		if(errFieldLists != null)	return errFieldLists;
		if(userService.validateUser(user.getEmail(), user.getPassword()) == true)	return new ResponseEntity<String>("Successfully, Logged-in", HttpStatus.OK);
		else	return new ResponseEntity<String>("Incorrect, credentials", HttpStatus.BAD_REQUEST); 
	}
}
//dont forget to check for //-character sequences