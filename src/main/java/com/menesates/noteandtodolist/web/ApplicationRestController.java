package com.menesates.noteandtodolist.web;

import com.menesates.noteandtodolist.exception.NoteNotFoundException;
import com.menesates.noteandtodolist.model.Note;
import com.menesates.noteandtodolist.service.NoteAndTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class ApplicationRestController {

    private final NoteAndTodoService noteAndTodoService;

    @Autowired
    public ApplicationRestController(NoteAndTodoService noteAndTodoService) {
        this.noteAndTodoService = noteAndTodoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/notes")
    public ResponseEntity<List<Note>> getNotes(Principal principal){
        List<Note> notes = noteAndTodoService.findNotes(principal.getName());
        return ResponseEntity.ok(notes);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/notes/{id}")
    public ResponseEntity<?> getNote(@PathVariable("id") Long id, Principal principal){
        try {
            Note note = noteAndTodoService.findNote(id, principal.getName());
            return ResponseEntity.ok(note);
        } catch (NoteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } // todo note kullanıcının değil hatası ekleyeceğim. yani yetkisiz erişim denemesi
    }
}
