package com.backend.tbdwm;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.backend.tbdwm.service.DictionaryService;
import com.backend.tbdwm.service.PersonService;
import com.backend.tbdwm.DTO.*;

@SpringBootApplication
//public class TbdwmApplication implements WebMvcConfigurer{
public class TbdwmApplication implements CommandLineRunner{
	@Autowired
	ApplicationContext context;
	@Autowired
	PersonService person;
	
	@Autowired
	DictionaryService dictionary;

	public static void main(String[] args) {
		SpringApplication.run(TbdwmApplication.class, args);
//		System.out.println("hello");
//		List<PersonDTO> result = person.getAllPersons();
//		for (PersonDTO dto : result) {
//			System.out.println(dto.toString());
//		}
//		System.out.println(dictionary.getDictionaryById(3L));
//		System.out.println(dictionary.getAllCommentsByDictionaryId(1L));
//		System.out.println(person.getPersonById(2L));
		
		
	}
	@Override
	public void run(String... args) throws Exception{
		System.out.println("hello");
		List<String> result = dictionary.getMeaningByWord("name");
		for (String dto : result) {
			System.out.println(dto.toString());
		}
//		System.out.println(person.getPersonById(2L).toString());
//		System.out.println(dictionary.getDictionaryByWord("suraj").toString());
		
	}

}
