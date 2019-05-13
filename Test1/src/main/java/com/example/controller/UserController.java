package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.model.UserInfo;
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
		
		if(userService.registerUser(email, user.getPassword()) != true)	return new ResponseEntity<String>("Email '" + email + "' already registered!", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<String>("Successfully, registered Email-id '" + email + "' !", HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody User user, BindingResult result){
		ResponseEntity<?> errFieldLists = mapValidation.mapValidationErrors(result);
		if(errFieldLists != null)	return errFieldLists;
		//System.out.println(user.getEmail() + " " + user.getPassword());
		if(userService.validateUser(user.getEmail(), user.getPassword()) == true)	return new ResponseEntity<String>("Successfully, Logged-in", HttpStatus.OK);
		else	return new ResponseEntity<String>("Incorrect, credentials", HttpStatus.BAD_REQUEST); 
	}
	
	@GetMapping("/ProfileInformation/{email}")
	public ResponseEntity<?> profileInformation(@PathVariable String email){
		UserInfo userInfo = userService.postProfileInfo(email.toLowerCase());
		//System.out.println(userInfo);

		if(userInfo == null)	return new ResponseEntity<String>("Invalid Email Address!", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
	}
	
	@PutMapping("/UpdateProfile")
	public ResponseEntity<?> updateProfile(@Valid @RequestBody UserInfo userInfo, BindingResult result){
		ResponseEntity<?> errFieldLists = mapValidation.mapValidationErrors(result);
		if(errFieldLists != null)	return errFieldLists;
		userService.getProfileInfo(userInfo);
		return new ResponseEntity<String>("Successfully updated profile", HttpStatus.OK);
	}
}
//update using the email address as the id
//dont forget to check for //-character sequences