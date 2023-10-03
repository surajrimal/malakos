package com.backend.tbdwm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Service;
import com.backend.tbdwm.entity.*;
import com.backend.tbdwm.repository.*;

import jakarta.transaction.Transactional;

import com.backend.tbdwm.DTO.*;
import com.backend.tbdwm.exception.*;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DictionaryService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Autowired
    private CommentRepository commentRepository;
    
    @Transactional
    public List<DictionaryDTO> getAllDictionaries() {
    	logger.info("Getting all dictionary details.");
        List<Dictionary> dictionaries = dictionaryRepository.findAll();
        return dictionaries.stream().map(Dictionary::fromEntity).collect(Collectors.toList());
    }
    @Transactional
    public DictionaryDTO getDictionaryById(Long id) {
    	logger.info("Getting a dictionary details for this {} id",id);
        Optional<Dictionary> dictionary = dictionaryRepository.findById(id);
        if (dictionary.isPresent()) {
        	Dictionary dictionaryEntity = dictionary.get();
        	DictionaryDTO dictionaryDTO = Dictionary.fromEntity(dictionaryEntity);
            return dictionaryDTO;
        } else {
        	throw new NotFoundException("Dictionary not found with id " + id);
        }
    }
    @Transactional
    public List<DictionaryDTO> getDictionaryByWord(String word) {
    	logger.info("Getting a dictionary details for this {} word",word);
        Optional<List<Dictionary>> dictionaries = Optional.ofNullable(dictionaryRepository.findAllByWord(word));
        if (dictionaries.isPresent()) {
        	List<Dictionary> dictionaries1 = dictionaries.get();
            return dictionaries1.stream().map(Dictionary::fromEntity).collect(Collectors.toList());
        } else {
        	throw new NotFoundException("Dictionary not found with word " + word);
        }
    }
    @Transactional
    public List<String> getMeaningByWord(String word) {
        Optional<List<Dictionary>> dictionaries = Optional.ofNullable(dictionaryRepository.findAllByWord(word));
        if (dictionaries.isPresent()) {
        	List<Dictionary> dictionaries1 = dictionaries.get();
        	List<DictionaryDTO>  dictionariesDTO = dictionaries1.stream().map(Dictionary::fromEntity).collect(Collectors.toList());
        	return dictionariesDTO.stream().map(dict -> dict.getMeaning()).collect(Collectors.toList());
        } else {
        	throw new NotFoundException("Dictionary not found with word " + word);
        }
    }

    public DictionaryDTO addDictionary(DictionaryDTO dictionaryDto) {
    	Dictionary dictionary = DictionaryDTO.toEntity(dictionaryDto);
        Dictionary savedDictionary = dictionaryRepository.save(dictionary);
        return Dictionary.fromEntity(savedDictionary);
    }

    public DictionaryDTO updateDictionary(Long id, DictionaryDTO dictionaryDto) {
        Optional<Dictionary> optionalDictionary = dictionaryRepository.findById(id);
        if (optionalDictionary.isPresent()) {
        	Dictionary dictionary = optionalDictionary.get();
            dictionary.setWord(dictionaryDto.getWord());
            dictionary.setMeaning(dictionaryDto.getMeaning());
            dictionary.setCreator(PersonDTO.toEntity(dictionaryDto.getCreator()));
            dictionaryRepository.save(dictionary);
            return Dictionary.fromEntity(dictionary);
        } else {
            throw new NotFoundException("Dictionary not found with id " + id);
        }
    }

    public void deleteDictionary(Long id) {
        Optional<Dictionary> dictionary = dictionaryRepository.findById(id);
        if (dictionary.isPresent()) {
            dictionaryRepository.delete(dictionary.get());
        } else {
            throw new NotFoundException("Dictionary not found with id " + id);
        }
    }

    public CommentDTO addComment(Long dictionaryId, CommentDTO commentDto) {
        Optional<Dictionary> optionalDictionary = dictionaryRepository.findById(dictionaryId);
        if (optionalDictionary.isPresent()) {
            Comment comment = CommentDTO.toEntity(commentDto);
            Comment savedComment = commentRepository.save(comment);
            return Comment.fromEntity(savedComment);
        } else {
            throw new NotFoundException("Dictionary not found with id " + dictionaryId);
        }
    }

    public List<CommentDTO> getAllCommentsByDictionaryId(Long dictionaryId) {
        Optional<Dictionary> optionalDictionary = dictionaryRepository.findById(dictionaryId);
        if (optionalDictionary.isPresent()) {
            List<Comment> comments = optionalDictionary.get().getComments();
            return comments.stream().map(Comment::fromEntity).collect(Collectors.toList());
        } else {
            throw new NotFoundException("Dictionary not found with id " + dictionaryId);
        }
    }

    public void deleteComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            commentRepository.delete(comment.get());
        } else {
            throw new NotFoundException("Comment not found with id " + commentId);
        }
    }
}
