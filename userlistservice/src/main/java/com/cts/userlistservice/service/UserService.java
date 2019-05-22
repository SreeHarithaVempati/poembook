package com.cts.userlistservice.service;

import com.cts.userlistservice.exception.UserAlreadyExistsException;
import com.cts.userlistservice.exception.UserNotFoundException;
import com.cts.userlistservice.model.User;

public interface UserService {

boolean saveUser(User user) throws UserAlreadyExistsException,UserNotFoundException;
	
	public User findByUserIdAndPassword(String userId,String password)  throws UserAlreadyExistsException,UserNotFoundException;
}




	
	
	


