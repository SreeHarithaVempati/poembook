package com.cts.userlistservice.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.userlistservice.exception.UserAlreadyExistsException;
import com.cts.userlistservice.exception.UserNotFoundException;
import com.cts.userlistservice.model.User;
import com.cts.userlistservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private final UserRepository userRepository ;
	
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		LOGGER.info("save user service started");
		Optional<User> u1=userRepository.findById(user.getUserId());
		if(u1.isPresent()){
			throw new UserAlreadyExistsException("User with this Id already exists ");
		}
		userRepository.save(user);
		LOGGER.info("save user service ended");
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		LOGGER.info("find all by userId and password service started");
		User user=userRepository.findByUserIdAndPassword(userId, password);
		if(user==null){
			throw new UserNotFoundException("User Id and password mismatch");
		}
		LOGGER.info("find all by userId and password service ended");
		return user;
	}

}





	
	
