package com.cts.userlistservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.userlistservice.model.User;


public interface UserRepository  extends JpaRepository<User,String> {

	
	User findByUserIdAndPassword(String userId, String password);
	 
	
	 @Query("Select user from User user where user.userId=(?1) and user.password=(?2)")
	User validate(String userId, String password);
	
}





