package com.backend.tbdwm.DTO;

import com.backend.tbdwm.entity.Person;

public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String username;
    private String password;

    // Constructors
    public PersonDTO() {}

    public PersonDTO(String firstName, String lastName, int age, String email, String username, String password) {
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
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	@Override
	public String toString() {
		return "PersonDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", email=" + email + ", username=" + username + ", password=" + password + "]";
	}

	public static Person toEntity(PersonDTO dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAge(dto.getAge());
        person.setEmail(dto.getEmail());
        person.setUsername(dto.getUsername());
        person.setPassword(dto.getPassword());
        return person;
    }
}
