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

import com.backend.tbdwm.DTO.PersonDTO;
import com.backend.tbdwm.service.PersonService;

@RestController
@CrossOrigin
@RequestMapping("/person")
public class PersonController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PersonService personService;
	
	//create a new dictionary word
	
	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public void createPerson(@RequestBody PersonDTO personDTO) {
		logger.info("Creation request for Person {}", personDTO);
		personService.addPerson(personDTO);
	}
	
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> getAllPersons() {
		logger.info("All dictionaries request");
		return personService.getAllPersons();
	}
	
	@GetMapping(value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public PersonDTO getPersonById(@PathVariable Long id) {
		logger.info("Person request by id {}", id);
		return personService.getPersonById(id);
	}
	
	

}
