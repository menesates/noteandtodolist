package com.menesates.noteandtodolist.service;

import com.menesates.noteandtodolist.dao.NoteRepository;
import com.menesates.noteandtodolist.exception.NoteNotFoundException;
import com.menesates.noteandtodolist.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@Secured(value = {"ROLE_USER", "ROLE_EDITOR", "ROLE_ADMIN"})
public class NoteAndTodoServiceImpl implements NoteAndTodoService {

    private NoteRepository noteRepository;

    @Autowired
    @Qualifier("noteRepositoryJpaImpl")
    public void setNoteRepository(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Note> findNotes(String username) {
        return noteRepository.findAll(username);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Note> findNotes(String username, String category) {
        return noteRepository.findByCategory(username,category);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Note findNote(Long id, String username) throws NoteNotFoundException {
        Note note = noteRepository.findById(id, username);
        if (note == null){
            throw new NoteNotFoundException("Note not found with id: " + id);
        } // todo hatayı yakalamalımı fırlatmalı mı
        return note;
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
    public void deleteNote(Long id, String username) {
        noteRepository.delete(id,username);
        // todo hatayı yakalamalımı fırlatmalı mı
    }
}
