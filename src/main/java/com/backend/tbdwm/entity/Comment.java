package com.backend.tbdwm.entity;


import java.util.Date;

import com.backend.tbdwm.DTO.CommentDTO;
import com.backend.tbdwm.DTO.PersonDTO;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private Date createdAt;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "creator_id", nullable = true)
    private Person author;

    public Comment() {
        this.createdAt = new Date();
    }

    public Comment(String text, Person author) {
        this.text = text;
        this.createdAt = new Date();
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}
    
	public static CommentDTO fromEntity(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setDate(comment.getCreatedAt());
        dto.setAuthor(Person.fromEntity(comment.getAuthor()));
        return dto;
    }
}
