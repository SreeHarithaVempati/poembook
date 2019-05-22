package com.cts.userlistservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import com.cts.userlistservice.model.User;
@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
	
	@Override
	public Map<String, String> generateToken(User user) {
		
		String jwtToken="";
		jwtToken=Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date()).
				signWith(SignatureAlgorithm.HS256,"secretkey").compact();
		Map<String ,String> map=new HashMap<>();
		map.put("token",jwtToken);
		map.put("Message","User Successfully logged in");
		return map;
	}

}






	
