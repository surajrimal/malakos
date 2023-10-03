package com.backend.tbdwm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.tbdwm.DTO.DictionaryDTO;
import com.backend.tbdwm.service.DictionaryService;

@RestController
@CrossOrigin
@RequestMapping("/dictionary")
public class DictionaryController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DictionaryService dictService;
	
	//create a new dictionary word
	
	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public void createDictionary(@RequestBody DictionaryDTO dictDTO) {
		logger.info("Creation request for dictionary {}", dictDTO);
		dictService.addDictionary(dictDTO);
	}
	
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public List<DictionaryDTO> getAllDictionaries() {
		logger.info("All dictionaries request");
		return dictService.getAllDictionaries();
	}
	
	@GetMapping(value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public DictionaryDTO getDictionaryById(@PathVariable Long id) {
		logger.info("dictionary request by id {}", id);
		return dictService.getDictionaryById(id);
	}
	
	@GetMapping(value = "word/{word}", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<String> getDictionaryById(@PathVariable String word) {
		logger.info("dictionary request by word {}", word);
		return dictService.getMeaningByWord(word);
	}

}
