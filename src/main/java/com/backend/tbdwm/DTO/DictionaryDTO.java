package com.backend.tbdwm.DTO;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.backend.tbdwm.entity.Dictionary;

public class DictionaryDTO {
    private Long id;
    private String word;
    private String meaning;
    private PersonDTO creator;
    private Date addedDate;
    private int likeCount;
    private List<CommentDTO> comments;
    
    // Constructors
    public DictionaryDTO() {}

    public DictionaryDTO(String word, String meaning, PersonDTO creator,int likeCount, List<CommentDTO> comments) {
        this.word = word;
        this.meaning = meaning;
        this.creator = creator;
        this.likeCount = likeCount;
        this.comments = comments;
    }

    // Getters and Setters
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

    public PersonDTO getCreator() {
        return creator;
    }

    public void setCreator(PersonDTO creator) {
        this.creator = creator;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
    
    
    
    @Override
	public String toString() {
		return "DictionaryDTO [id=" + id + ", word=" + word + ", meaning=" + meaning + ", creator=" + creator
				+ ", addedDate=" + addedDate + ", likeCount=" + likeCount + ", comments=" + comments + "]";
	}

	public static Dictionary toEntity(DictionaryDTO dto) {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(dto.getId());
        dictionary.setWord(dto.getWord());
        dictionary.setMeaning(dto.getMeaning());
        dictionary.setLikeCount(dto.getLikeCount());
        dictionary.setAdded(dto.getAddedDate());
        dictionary.setCreator(PersonDTO.toEntity(dto.getCreator()));
        dictionary.setComments(dto.getComments().stream()
                .map(CommentDTO::toEntity)
                .collect(Collectors.toList()));
        return dictionary;
    }
}
