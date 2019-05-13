package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.model.UserInfo;
import com.example.repository.UserInfoRepository;
import com.example.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserInfoRepository userInfoRepository;

	public String retrieveFormattedPassword(String oldPassword) {
		
		String newPassword = "", delimiter = "";int ind = 0;int tmp;char tmp1, tmp2;
		int a = Integer.valueOf('a'), z = Integer.valueOf('z'), Z = Integer.valueOf('Z'), A = Integer.valueOf('A');

		for(int i = 0; i < oldPassword.length(); i++) {	
			tmp1 = oldPassword.charAt(i);tmp = Integer.valueOf(tmp1);
			
			if((tmp >= a && tmp <= z) || (tmp >= A && tmp <= Z))	newPassword += oldPassword.charAt(i);
			else if((Integer.valueOf(String.valueOf(tmp1)) >= 0 && Integer.valueOf(String.valueOf(tmp1)) <= 9)){
				
				if(ind == 1) {
					delimiter += tmp1;
					if(delimiter.length() >= 4) {
						if(delimiter.substring(delimiter.length() - 4, delimiter.length()).equals("9292")) {
							tmp2 = (char)Integer.parseInt(delimiter.substring(0, delimiter.length() - 4));
							newPassword += tmp2;delimiter = "";ind = 0;
						}
					}
				}else {
					
					delimiter += tmp1;
					if(delimiter.length() >= 4) {
						if(delimiter.substring(delimiter.length() - 4, delimiter.length()).equals("4747")) {
							newPassword += delimiter.substring(0, delimiter.length() - 4);
							delimiter = "";ind = 1;
						}
					}
				}
			}
		}
		newPassword += delimiter;
		return newPassword;
	}
	
	public boolean validateUser(String email, String password) {
		User user = userRepository.findUserByEmail(email.toLowerCase());
		//System.out.println(user.getEmail() + " " + user.getPassword() + " " + password);
		if(user == null || !password.equals(user.getPassword()))	return false;
		return true;
	}
	
	public boolean registerUser(String email, String password) {
		email = email.toLowerCase();
		password = password.toLowerCase();
		//password = retrieveFormattedPassword(password.toLowerCase());
		User user = userRepository.findUserByEmail(email);

		if(user != null)	return false;
		user = new User();
		user.setEmail(email);
		user.setPassword(password);
		userRepository.save(user);
		return true;
	}
	
	public boolean updateAccountBalance(String email, String password) {
		//not completely sure of what we are going to do here
		return true;
	}
	
	public UserInfo postProfileInfo(String email) {
		User user = userRepository.findUserByEmail(email);
		
		if(user != null) {
			UserInfo userInfo = userInfoRepository.findByUser(user);
			if(userInfo == null) {
				userInfo = new UserInfo();
				userInfo.setEmail(email);
			}else 	return userInfo;
			return userInfo;
		}
		return null;
	}
	
	public void getProfileInfo(UserInfo userInfo) {
		User user = userRepository.findUserByEmail(userInfo.getEmail().toLowerCase());
		System.out.println(userInfo);
		if(user != null) {
			
			UserInfo userInfoOld = userInfoRepository.findByUser(user);
			if(userInfoOld == null)		userInfoOld = new UserInfo();
			userInfoOld.mergeUpdate(userInfo);
			userInfoOld.setUser(user);
			System.out.println(userInfoOld);
			userInfoRepository.save(userInfoOld);
		}
	}
}
//Think of including password requirement 


/*
	Creating my own access token
	**Requirements
	*It has information specific about the user
	*It has contain information about the lifecycle of the token
	*
	*the password in the db has to 
*/