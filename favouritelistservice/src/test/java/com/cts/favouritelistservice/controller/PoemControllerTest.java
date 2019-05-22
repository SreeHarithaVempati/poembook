package com.cts.favouritelistservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.favouritelistservice.domain.Poem;
import com.cts.favouritelistservice.exception.PoemAlreadyExistsException;
import com.cts.favouritelistservice.exception.PoemNotFoundException;
import com.cts.favouritelistservice.service.PoemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(PoemController.class)
public class PoemControllerTest {
	
	
		@Autowired
		private transient MockMvc mvc;

		@MockBean
		private transient PoemService poemService;

		private transient Poem poem;

		@InjectMocks
		private PoemController poemController;

		static List<Poem> poems;

		@Before
		public void setUp() {
			MockitoAnnotations.initMocks(this);

			mvc = MockMvcBuilders.standaloneSetup(poemController).build();
			poems = new ArrayList<>();
			poem = new Poem("shakespeare2","good","shakespeare22","sree11",true);
			poems.add(poem);
			poem = new Poem("shakespeare3","good","shakespeare32","sree11",true);
			poems.add(poem);
		}

		@Test
		public void testSaveNewPoemSuccess() throws Exception {
			String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzcmVlMTEiLCJpYXQiOjE1NTM5MjMxMjZ9.pRX-LTgUMouiwKfouqs6sdn3_dM71sU_yVhJ0ZJHbkw";
			
			when(poemService.savePoem(poem)).thenReturn(true);
			mvc.perform(post("/api/v1/favouritelistservice/poem").header("authorization", "Bearer " + token).
					contentType(MediaType.APPLICATION_JSON).content(jsonToString(poem)))
			.andExpect(status().isCreated());
			verify(poemService, times(1)).savePoem(Mockito.any(Poem.class));
			verifyNoMoreInteractions(poemService);
		}
		@Test
	     public void testSaveNewPoemUnSuccessful() throws Exception{
			 
	     when(poemService.savePoem(Mockito.any(Poem.class))).thenThrow(new PoemAlreadyExistsException("Poem Already Exist"));
			String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzcmVlMTEiLCJpYXQiOjE1NTM5MjMxMjZ9.pRX-LTgUMouiwKfouqs6sdn3_dM71sU_yVhJ0ZJHbkw";
	                                   
	     mvc.perform(post("/api/v1/favouritelistservice/poem").header("authorization", "Bearer " + token)
	                    .contentType(MediaType.APPLICATION_JSON).content(jsonToString(poem))).andExpect(status().isConflict());
	     verify(poemService, times(1)).savePoem(Mockito.any(Poem.class));
	     verifyNoMoreInteractions(poemService);
	                }



		@Test
		public void testDeletePoemById() throws Exception {
			when(poemService.deletePoemById(1)).thenReturn(true);
			mvc.perform(delete("/api/v1/favouritelistservice/poem/{id}", 3)).andExpect(status().isOk());
			verify(poemService, times(1)).deletePoemById(3);
			verifyNoMoreInteractions(poemService);
		}
		@Test
	     public void testDeletePoemByIdUnsuccessful() throws Exception {
	     when(poemService.deletePoemById(5)).thenThrow(new PoemNotFoundException("Poem Not Found"));
	     mvc.perform(delete("/api/v1/favouritelistservice/poem/{id}",5)).andExpect(status().isNotFound());
	     verify(poemService, times(1)).deletePoemById(5);
	     verifyNoMoreInteractions(poemService);
	                }



		@Test
		public void testGetMyPoems() throws Exception {
			String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzcmVlMTEiLCJpYXQiOjE1NTM5MjMxMjZ9.pRX-LTgUMouiwKfouqs6sdn3_dM71sU_yVhJ0ZJHbkw";
			when(poemService.getMyPoems("sree11")).thenReturn(null);
			mvc.perform(get("/api/v1/favouritelistservice/poem").header("authorization", "Bearer " + token)
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
			verify(poemService, times(1)).getMyPoems("sree11");
			verifyNoMoreInteractions(poemService);
		}

		private String jsonToString(final Object object) {
			String result;
			try {
				final ObjectMapper mapper = new ObjectMapper();
				result = mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				result = "Json processing error";
			}
			return result;
		}
	}


