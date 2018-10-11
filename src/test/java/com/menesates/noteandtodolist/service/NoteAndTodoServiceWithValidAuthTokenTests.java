package com.menesates.noteandtodolist.service;

import com.menesates.noteandtodolist.exception.NoteNotFoundException;
import com.menesates.noteandtodolist.model.Note;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
@Transactional
public class NoteAndTodoServiceWithValidAuthTokenTests {

    @Autowired
    private NoteAndTodoService noteAndTodoService;

    @Before
    public void setUp(){
        TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken("user1",
                "secret",
                "ROLE_USER");
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @After
    public void tearDown(){
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testFindNotes(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Note> notes = noteAndTodoService.findNotes(username);

        MatcherAssert.assertThat(notes.size(), Matchers.equalTo(3));
        IntStream.range(0,notes.size())
                .forEach(i -> MatcherAssert.assertThat(notes.get(i).getUsername(),Matchers.equalTo("user1")));

    }

    @Test(expected = NoteNotFoundException.class)
    public void testFindNoteByIdNotFound(){
        Note note = noteAndTodoService.findNote(100L);
    }

    @Test
    public void testFindNoteValidById(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Note note = new Note();
        note.setHeader("Service Test Create Note");
        note.setBody("Service Test");
        note.setUsername(username);
        note.setCategory("danger");

        noteAndTodoService.createNote(note);

        Note find = noteAndTodoService.findNote(note.getId());
        MatcherAssert.assertThat(note.getId(),Matchers.equalTo(find.getId()));

        noteAndTodoService.deleteNote(note.getId());
    }

    @Test
    public void testFindNoteByCategory(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Note> notes = noteAndTodoService.findNotes(username,"danger");
        MatcherAssert.assertThat(notes.size(),Matchers.equalTo(2));
        IntStream.range(0,notes.size()).
                forEach(i -> MatcherAssert.assertThat(notes.get(i).getCategory(),Matchers.equalTo("danger")));
    }

    @Test
    public void testCreateNote(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Note note = new Note();
        note.setHeader("Service Test Create Note");
        note.setBody("Service Test");
        note.setUsername(username);
        note.setCategory("danger");

        noteAndTodoService.createNote(note);

        Note find = noteAndTodoService.findNote(note.getId());
        MatcherAssert.assertThat(note.getUsername(),Matchers.equalTo(find.getUsername()));

        noteAndTodoService.deleteNote(note.getId());
    }

    @Test
    public void testUpdateNote(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Note note = new Note();
        note.setHeader("Service Test Update Note");
        note.setBody("Service Test");
        note.setUsername(username);
        note.setCategory("danger");

        noteAndTodoService.createNote(note);

        Note update = new Note();
        update.setHeader("Service Test Update New Note");
        update.setBody("Service Test");
        update.setUsername(username);
        update.setCategory("succes");

        noteAndTodoService.updateNote(update);

        Note result = noteAndTodoService.findNote(update.getId());

        MatcherAssert.assertThat(result.getId(),Matchers.equalTo(update.getId()));
        MatcherAssert.assertThat(result.getCategory(),Matchers.equalTo("succes"));
    }

    @Test(expected = NoteNotFoundException.class)
    public void testDeleteNote(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Note note = new Note();
        note.setHeader("Service Test Delete Note");
        note.setBody("Service Test");
        note.setUsername(username);
        note.setCategory("danger");

        noteAndTodoService.createNote(note);

        noteAndTodoService.deleteNote(note.getId());

        Note find = noteAndTodoService.findNote(note.getId());
    }
}
