package com.cts.favouritelistservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.cts.favouritelistservice.FavouritelistserviceApplication;
import com.cts.favouritelistservice.domain.Poem;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=FavouritelistserviceApplication.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class PoemRepositoryTest {

		@Autowired
		private transient PoemRepository poemRepository;

		public void setRepo(PoemRepository poemRepository) {
			this.poemRepository = poemRepository;
		}
		@After
		public void tearDown() {

			poemRepository.deleteAllInBatch();

		}

		@Test
		public void testSavePoem() throws Exception {
			
			final Poem poem =poemRepository.save( new Poem("shakespeare","shakespeare2","good","sree11",true));
			assertThat(poem.getAuthor()).isEqualTo("shakespeare2");
			poemRepository.deleteAllInBatch();
		}
		
		@Test
		public void testForUpdatingMovie() throws Exception {
			
			final Poem poem  = poemRepository.save(new Poem("shakespeare","shakespeare2","good","sree11",true));
			assertEquals(poem.getAuthor(), "shakespeare2");
			poem.setComments("Nice");
			final Poem testPoem = poemRepository.save(poem);
			assertEquals(testPoem.getComments(), "Nice");
			poemRepository.deleteAllInBatch();

		}
		
		@Test
		public void testForDeletingMovie() throws Exception {
			
			final Poem poem  = poemRepository.save(new Poem("shakespeare","shakespeare2","good","sree11",true));
			assertEquals(poem.getAuthor(), "shakespeare2");
			poemRepository.delete(poem);
			assertEquals(Optional.empty(), poemRepository.findById(1));
			
		}



		@Test
		public void testGetMyPoems() throws Exception {
			poemRepository.save( new Poem("shakespeare","shakespeare2","good","sree11",true));
			poemRepository.save( new Poem("shakespeare","shakespeare3","good","sree11",true));
			final List<Poem> poems = poemRepository.findByUserId("sree11");
			assertThat(poems.size()>0);
			poemRepository.deleteAllInBatch();
		}

	
	
}
