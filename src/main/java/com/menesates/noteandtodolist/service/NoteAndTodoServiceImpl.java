package com.menesates.noteandtodolist.service;

import com.menesates.noteandtodolist.dao.NoteRepository;
import com.menesates.noteandtodolist.exception.NoteNotFoundException;
import com.menesates.noteandtodolist.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteAndTodoServiceImpl implements NoteAndTodoService {

    private NoteRepository noteRepository;

    @Autowired
    public void setNoteRepository(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findNotes(String username) {
        return noteRepository.findAll(username);
    }

    @Override
    public List<Note> findNotes(String username, String category) {
        return noteRepository.findByCategory(username,category);
    }

    @Override
    public Note findNote(Long id) throws NoteNotFoundException {
        Note note = noteRepository.findById(id);
        if (note == null){
            throw new NoteNotFoundException("Note not found with id: " + id);
        }
        return noteRepository.findById(id);
    }

    @Override
    public void createNote(Note note) {
        noteRepository.create(note);
    }

    @Override
    public void updateNote(Note note) {
        noteRepository.update(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.delete(id);
    }
}
