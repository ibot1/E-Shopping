package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;
import com.example.model.UserInfo;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long>{
	
	UserInfo findByUser(User user);
}
