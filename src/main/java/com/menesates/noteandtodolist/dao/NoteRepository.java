package com.menesates.noteandtodolist.dao;

import com.menesates.noteandtodolist.model.Note;

import java.util.List;

public interface NoteRepository {
    List<Note> findAll(String username);
    Note findById(Long id, String username);
    List<Note> findByCategory(String username, String category);
    void create(Note note);
    Note update(Note note);
    void delete(Long id, String username);
}
