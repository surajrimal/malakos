package com.backend.tbdwm.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.tbdwm.entity.*;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long>{
	List<Dictionary> findAllByWord(String word);
	
}
