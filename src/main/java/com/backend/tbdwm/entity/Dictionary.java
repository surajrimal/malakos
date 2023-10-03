package com.backend.tbdwm.entity;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import com.backend.tbdwm.DTO.DictionaryDTO;
import com.backend.tbdwm.DTO.PersonDTO;

import jakarta.persistence.*;

@Entity
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String word;

    private String meaning;

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id", nullable = true)
    private Person creator;

    @Temporal(TemporalType.TIMESTAMP)
    private Date added;
    
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name="comment_id")
    private List<Comment> comments = new ArrayList<>();
    @Column(name="like_count")
    private int likeCount = 0;

    public Dictionary() {
    	this.added = new Date();
    }

    public Dictionary(String word, String meaning, Person creator) {
        this.word = word;
        this.meaning = meaning;
        this.creator = creator;
        this.added = new Date();
        this.likeCount = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int like_count) {
        this.likeCount = like_count;
    }

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public static DictionaryDTO fromEntity(Dictionary dictionary) {
        DictionaryDTO dto = new DictionaryDTO();
        dto.setId(dictionary.getId());
        dto.setWord(dictionary.getWord());
        dto.setMeaning(dictionary.getMeaning());
        dto.setLikeCount(dictionary.getLikeCount());
        dto.setAddedDate(dictionary.getAdded());
        
     // Fetch required properties of Person within an active Hibernate session
        Person creator = dictionary.getCreator();
        Hibernate.initialize(creator); // Initialize the proxy object
        
        dto.setCreator(Person.fromEntity(creator));
        dto.setComments(dictionary.getComments().stream()
                .map(Comment::fromEntity)
                .collect(Collectors.toList()));
        
        return dto;
    }
	
    
}
