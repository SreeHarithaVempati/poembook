package com.cts.userlistservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.userlistservice.exception.UserAlreadyExistsException;
import com.cts.userlistservice.model.User;
import com.cts.userlistservice.service.SecurityTokenGenerator;
import com.cts.userlistservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import static org.mockito.Mockito.verifyNoMoreInteractions;



@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
		@Autowired
		private MockMvc mockMvc;
		
		@MockBean
		private UserService userService;
		
		@MockBean 
		private SecurityTokenGenerator tokenGenerator;
		
		private User user;
		
		@InjectMocks
		private UserController userController;
		
		@Before 
		public void setUp(){
			MockitoAnnotations.initMocks(this);
			user=new User("Jack","Jack Jenny","Jen","123456",new Date());
		}
		
		@Test
		public void testRegisterUser() throws  Exception{
			when(userService.saveUser(user)).thenReturn(true);
			mockMvc.perform(post("/api/v1/userlistservice/register").contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isCreated());
			verify(userService,times(1)).saveUser(Mockito.any(User.class));
			verifyNoMoreInteractions(userService);
			
			
		}
		
		@Test
	     public void testRegisterUserUnsuccessful() throws Exception {
	    when(userService.saveUser(Mockito.any(User.class))).thenThrow(new UserAlreadyExistsException("User ALready Exist"));

	    mockMvc.perform(post("/api/v1/userlistservice/register").contentType(MediaType.APPLICATION_JSON)
	                                                                                .accept(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isConflict());

	    verify(userService, times(1)).saveUser(Mockito.any(User.class));
	      Mockito.verifyNoMoreInteractions(userService);

	                                }

		
		@Test
		public void testLoginUser()throws  Exception{
			String userId="Jack";
			String password="123456";
			when(userService.saveUser(user)).thenReturn(true);
			when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
			mockMvc.perform(post("/api/v1/userlistservice/login").contentType(MediaType.APPLICATION_JSON).
					content(jsonToString(user))).andExpect(status().isOk());
			verify(userService,times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
			verifyNoMoreInteractions(userService);
		}
		
		
		
		 private static String jsonToString(final Object obj) throws JsonProcessingException
		    {
		    	String result;
		    	try{
		    		final ObjectMapper mapper =new ObjectMapper();
		    		final String jsonContent=mapper.writeValueAsString(obj);
		    		result=jsonContent;
		    	
		    	}
		    	catch(JsonProcessingException e)
		    	{
		    		result="Json Processing Error";
		    	}
		    	
		    	return result;
		    }

}
