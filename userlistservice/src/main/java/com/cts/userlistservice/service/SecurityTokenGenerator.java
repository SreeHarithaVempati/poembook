package com.cts.userlistservice.service;

import java.util.Map;

import com.cts.userlistservice.model.User;

public interface SecurityTokenGenerator {
	Map<String,String> generateToken(User user);
}








	