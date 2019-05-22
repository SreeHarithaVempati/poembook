package com.cts.favouritelistservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.favouritelistservice.domain.Poem;
import com.cts.favouritelistservice.exception.PoemAlreadyExistsException;
import com.cts.favouritelistservice.exception.PoemNotFoundException;
import com.cts.favouritelistservice.service.PoemService;


@RestController
@RequestMapping("/api/v1/favouritelistservice")
@CrossOrigin
public class PoemController {
	private PoemService poemService;

	@Autowired
	public PoemController(PoemService poemService) {
		super();
		this.poemService = poemService;
	}

	@PostMapping("/poem")
	public ResponseEntity<?> saveNewPoem(@RequestBody final Poem poem, final HttpServletRequest request,
			final HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		try {
			poem.setUserId(userId);
			poem.setFavourite(true);
			System.out.println(poem.getUserId());
			System.out.println(poem.getTitle());

			poemService.savePoem(poem);
			responseEntity = new ResponseEntity<Poem>(poem, HttpStatus.CREATED);
		} catch (PoemAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PutMapping("/poem/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") final Integer id, @RequestBody Poem poem )
	{
		ResponseEntity<?> responseEntity;
		try{
			final Poem fetchedPoem=poemService.updatePoem(poem);
			responseEntity=new ResponseEntity<Poem>(fetchedPoem,HttpStatus.OK);
		}
		catch(PoemNotFoundException e){
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@DeleteMapping(path = "/poem/{id}")
	public ResponseEntity<?> deletePoemById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			poemService.deletePoemById(id);
			responseEntity = new ResponseEntity<String>("{ \" message\": \"Poem deleted successfully\"}", HttpStatus.OK);
		} catch (PoemNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	

	@GetMapping("/poem")
	public ResponseEntity<?> getMyPoems(final HttpServletRequest request, final HttpServletResponse response) {
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		List<Poem> poems = poemService.getMyPoems(userId);
		ResponseEntity<?> responseEntity = new ResponseEntity<List<Poem>>(poems, HttpStatus.OK);
		return responseEntity;
	}


}









	