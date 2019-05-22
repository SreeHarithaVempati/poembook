package com.cts.userlistservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cts.userlistservice.model.User;
import com.cts.userlistservice.service.SecurityTokenGenerator;
import com.cts.userlistservice.service.UserService;

@RestController
@EnableWebMvc
@RequestMapping("/api/v1/userlistservice")
@CrossOrigin
public class UserController {
	
@Autowired private UserService userService;
	
	@Autowired private SecurityTokenGenerator tokenGenerator;
	
	
	@PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user)
    {
		try{
			userService.saveUser(user);
			return new ResponseEntity<String>("User registered successfully",HttpStatus.CREATED);
		}
		catch(Exception e){
			return new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.CONFLICT);
			
		}
    }
	
	@PostMapping("/login")
	 public ResponseEntity<?> loginUser(@RequestBody User loginDetail)
	 {
		try{
			String userId=loginDetail.getUserId();
			String password=loginDetail.getPassword();
			if(null == loginDetail.getUserId() || null == loginDetail.getPassword()){
				throw new Exception("User name and password cannot be empty");
			}
			User user=userService.findByUserIdAndPassword(userId, password);
			if(user==null){
				throw new Exception("User with given Id doesnot exists");
			}
			String pwd=user.getPassword();
			if(!password.equals(pwd)){
				throw new Exception("Invalid login credentails.Please check user name and password");
			}
			Map<String,String> map=tokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.UNAUTHORIZED);	
		}
	 }
	

}






	
	
	
