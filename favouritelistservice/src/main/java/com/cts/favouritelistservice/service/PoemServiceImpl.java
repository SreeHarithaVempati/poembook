package com.cts.favouritelistservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.favouritelistservice.domain.Poem;
import com.cts.favouritelistservice.exception.PoemAlreadyExistsException;
import com.cts.favouritelistservice.exception.PoemNotFoundException;
import com.cts.favouritelistservice.repository.PoemRepository;


@Service
public class PoemServiceImpl implements PoemService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PoemServiceImpl.class);
	
	private final transient PoemRepository poemRepository;

	@Autowired
	public PoemServiceImpl(PoemRepository poemRepository) {
		super();
		this.poemRepository = poemRepository;
	}
	
	
	@Override
	public Poem updatePoem(Poem updatePoem) throws PoemNotFoundException {
		LOGGER.info("update service started");
		final Poem poem = poemRepository.findById(updatePoem.getId()).orElse(null);
		if (poem == null) {
			throw new PoemNotFoundException("Could not update. Movie not found");
		}
		poem.setComments(updatePoem.getComments());
		LOGGER.debug("comment data=> {}",poem.getComments());
		poemRepository.save(poem);
		LOGGER.info("update service ended");
		return poem;
	}
	

	@Override
	public boolean savePoem(Poem poem) throws PoemAlreadyExistsException {
		LOGGER.info("save poem service started");
		final Optional<Poem> object = poemRepository.findByTitleAndUserId(poem.getTitle(), poem.getUserId());
		if (object.isPresent()) {
			throw new PoemAlreadyExistsException("Could not save poem, Poem already exists");
		}
		LOGGER.debug("save poem data=> {}",poem);
		poemRepository.save(poem);
		LOGGER.info("save poem service Ended");
		return true;
	}

	@Override
	public boolean deletePoemById(int  id) throws PoemNotFoundException {
		LOGGER.info("delete poem service started");
		final Poem poem = poemRepository.findById(id).orElse(null);
		if (poem == null) {
			throw new PoemNotFoundException("Could not delete. Poem not found");
		}
		LOGGER.debug("delete poem data=> {}",poem);
		poemRepository.delete(poem);
		LOGGER.info("delete poem service ended");
		return true;
	}
	

	@Override
	public List<Poem> getMyPoems(String userId) {
		LOGGER.info("get all poems service started");
		return poemRepository.findByUserId(userId);
	}
}









	
