package com.menesates.noteandtodolist.dao;

import com.menesates.noteandtodolist.model.Note;

import java.util.List;

public interface NoteRepository {
    List<Note> findAll(String username);
    Note findById(Long id);
    List<Note> findByCategory(String category);
    void create(Note note);
    Note update(Note note);
    void delete(Long id);
}