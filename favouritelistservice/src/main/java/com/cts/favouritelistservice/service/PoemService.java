package com.cts.favouritelistservice.service;

import java.util.List;

import com.cts.favouritelistservice.domain.Poem;
import com.cts.favouritelistservice.exception.PoemAlreadyExistsException;
import com.cts.favouritelistservice.exception.PoemNotFoundException;



public interface PoemService {
	
boolean savePoem(Poem poem) throws PoemAlreadyExistsException;
	
	boolean deletePoemById(int id) throws PoemNotFoundException;
	
	List<Poem> getMyPoems(String userId);
	
	Poem updatePoem(Poem poem) throws PoemNotFoundException;

}
