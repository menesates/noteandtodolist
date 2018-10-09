package com.menesates.noteandtodolist.service;

import com.menesates.noteandtodolist.exception.NoteNotFoundException;
import com.menesates.noteandtodolist.model.Note;

import java.util.List;

public interface NoteAndTodoService {
    List<Note> findNotes(String username);
    List<Note> findNotes(String username, String category);
    Note findNote(Long id) throws NoteNotFoundException;
    void createNote(Note note);
    void updateNote(Note note);
    void deleteNote(Long id);
}
