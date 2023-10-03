package com.backend.tbdwm.service;


import com.backend.tbdwm.DTO.DictionaryDTO;
import com.backend.tbdwm.DTO.PersonDTO;
import com.backend.tbdwm.entity.Dictionary;
import com.backend.tbdwm.entity.Person;
import com.backend.tbdwm.exception.NotFoundException;
import com.backend.tbdwm.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDTO> getAllPersons() {
    	List<Person> persons = personRepository.findAll();
        return persons.stream().map(Person::fromEntity).collect(Collectors.toList());
    }

    public PersonDTO getPersonById(Long id) {
    	Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
        	Person personEntity = person.get();
        	PersonDTO personDTO = Person.fromEntity(personEntity);
            return personDTO;
        } else {
        	throw new NotFoundException("Person not found with id " + id);
        }
    }
  

    public PersonDTO addPerson(PersonDTO personDto) {
    	Person person = PersonDTO.toEntity(personDto);
    	Person savedPerson = personRepository.saveAndFlush(person);
        return Person.fromEntity(savedPerson);
        
    }

    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
    
    public Person updatePerson(Person updatedPerson) {
        return personRepository.save(updatedPerson);
    }
}
