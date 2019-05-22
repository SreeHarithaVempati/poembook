package com.cts.favouritelistservice.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.favouritelistservice.domain.Poem;
import com.cts.favouritelistservice.exception.PoemAlreadyExistsException;
import com.cts.favouritelistservice.exception.PoemNotFoundException;
import com.cts.favouritelistservice.repository.PoemRepository;


public class FavouriteListServiceImplTest {
	
	
		@Mock
		private transient PoemRepository poemRepository;

		private transient Poem poem;

		@InjectMocks
		private transient PoemServiceImpl poemServiceImpl;

		transient Optional<Poem> options;

		@Before
		public void setupMock() {
			MockitoAnnotations.initMocks(this);
			poem = new Poem("shakespeare22","good","shakespeare22","sree11",true);
			options = Optional.of(poem);
		}

		@Test
		public void testMockCreation() {
			assertNotNull("JpaRepository creation failed: use @InjectMocks on matchServiceImpl", poem);
		}

		@Test
		public void testSavepoemSuccess() throws PoemAlreadyExistsException {
			when(poemRepository.save(poem)).thenReturn(poem);
			final boolean flag = poemServiceImpl.savePoem(poem);
			assertTrue("saving match failed", flag);
			verify(poemRepository, times(1)).save(poem);
		}
		@Test(expected = PoemAlreadyExistsException.class)
		public void testSavePoemFailure() throws PoemAlreadyExistsException {
			when(poemRepository.findByTitleAndUserId("shakespeare22", "sree11")).thenReturn(options);
			when(poemRepository.save(poem)).thenReturn(poem);
			final boolean flag = poemServiceImpl.savePoem(poem);
			assertTrue("saving movie failed", flag);
			verify(poemRepository, times(1)).findByTitleAndUserId(poem.getTitle(),poem.getUserId());
		}

		@Test
		public void testDeletepoemById() throws PoemNotFoundException {
			when(poemRepository.findById(poem.getId())).thenReturn(options);
			doNothing().when(poemRepository).delete(poem);
			final boolean flag = poemServiceImpl.deletePoemById(poem.getId());
			assertTrue("deleting match failed", flag);
			verify(poemRepository, times(1)).delete(poem);
			verify(poemRepository, times(1)).findById(poem.getId());
		}


		@Test
		public void testGetAllPoems() throws PoemNotFoundException {
			final List<Poem> poems = new ArrayList<>();
			poems.add(poem);
			when(poemRepository.findByUserId(poem.getUserId())).thenReturn(poems);
			final List<Poem> poems1 = poemServiceImpl.getMyPoems("sree11");
			assertEquals(poems, poems1);
			verify(poemRepository, times(1)).findByUserId("sree11");
		}
		
	


}
