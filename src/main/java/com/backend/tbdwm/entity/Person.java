package com.backend.tbdwm.entity;

import jakarta.persistence.*;


import com.backend.tbdwm.DTO.PersonDTO;

import java.util.Collection;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable =false, length =50)
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String username;
    private String password;

    // Constructors
    public Person() {}

    public Person(String firstName, String lastName, int age, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

 
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public static PersonDTO fromEntity(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAge(person.getAge());
        dto.setEmail(person.getEmail());
        dto.setUsername(person.getUsername());
        dto.setPassword(person.getPassword());
        return dto;
    }
}

