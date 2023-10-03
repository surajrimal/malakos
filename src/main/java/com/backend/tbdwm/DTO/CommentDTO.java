package com.backend.tbdwm.DTO;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.backend.tbdwm.entity.Comment;

public class CommentDTO {

    private Long id;
    private String text;
    private PersonDTO author;
    private Date date;

    // Constructors
    public CommentDTO() {}

    public CommentDTO(String text, PersonDTO author) {
        this.text = text;
        this.author = author;
    }

    // Getters and Setters
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

    public PersonDTO getAuthor() {
        return author;
    }

    public void setAuthor(PersonDTO author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", text=" + text + ", author=" + author + ", date=" + date + "]";
	}
	
	public static Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setText(dto.getText());
        comment.setCreatedAt(dto.getDate());
        comment.setAuthor(PersonDTO.toEntity(dto.getAuthor()));
        return comment;
    }
    
}
