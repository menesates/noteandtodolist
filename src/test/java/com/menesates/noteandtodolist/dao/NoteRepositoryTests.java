package com.menesates.noteandtodolist.dao;

import com.menesates.noteandtodolist.model.Note;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // method adlarına gore calistir
public class NoteRepositoryTests {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    // method adı calistirma sirasi icin degistirildi
    public void a_getNotesTest(){
        List<Note> notes = noteRepository.findAll("user1");

        MatcherAssert.assertThat(notes.size(), Matchers.equalTo(3));
        System.out.println(notes);
        MatcherAssert.assertThat(notes.get(0).getCategory(), Matchers.equalTo("danger"));
        MatcherAssert.assertThat(notes.get(1).getCategory(), Matchers.equalTo("info"));
        MatcherAssert.assertThat(notes.get(2).getCategory(), Matchers.equalTo("danger"));
    }

    @Test
    // method adı calistirma sirasi icin degistirildi
    public void a_findByIdTest(){
        Note note = noteRepository.findById(1L);
        MatcherAssert.assertThat(note.getUsername(),Matchers.equalTo("user1"));
    }

    @Test(expected = NullPointerException.class)
    // method adı calistirma sirasi icin degistirildi
    public void a_findByIdNotFoundTest(){
        Note note = noteRepository.findById(10L);
        MatcherAssert.assertThat(note.getUsername(),Matchers.equalTo("user1"));
    }

    @Test
    public void findByCategoryTest(){
        List<Note> notes = noteRepository.findByCategory("user1","danger");
        IntStream.range(0, notes.size()).forEach(i -> MatcherAssert.assertThat(notes.get(i).getUsername(), Matchers.equalTo("user1")));
    }

    @Test
    public void createTest(){
        Note note = new Note();
        note.setId(10L);
        note.setHeader("Note 10 Test Header");
        note.setBody("Note 10 Test Body");
        note.setUsername("user4");
        note.setCategory("succes");

        noteRepository.create(note);
        Note result = noteRepository.findById(10L);
        MatcherAssert.assertThat(note.getId(),Matchers.equalTo(10L));
    }

    @Test
    public void updateTest(){
        Note note = new Note();
        note.setId(1L);
        note.setHeader("Note 1 update Test Header");
        note.setBody("Note 1 update Test Body");
        note.setUsername("user4");
        note.setCategory("succes");

        Note result = noteRepository.update(note);
        MatcherAssert.assertThat(note.getUsername(),Matchers.equalTo("user4"));
    }

    @Test(expected = NullPointerException.class)
    public void deleteTest(){
        noteRepository.delete(1L);
        Note note = noteRepository.findById(1L);
        System.out.println(note);
        MatcherAssert.assertThat(note.getUsername(),Matchers.equalTo("user1"));
    }
}
