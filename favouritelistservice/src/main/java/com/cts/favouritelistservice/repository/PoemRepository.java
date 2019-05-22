package com.cts.favouritelistservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.favouritelistservice.domain.Poem;




public interface PoemRepository  extends JpaRepository<Poem, Integer> {

	
	Optional<Poem> findByTitle(String title);

	List<Poem> findByUserId(String userId);

	Optional<Poem> findByTitleAndUserId(String title, String getUserId);
}
