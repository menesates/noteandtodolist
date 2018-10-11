package com.menesates.noteandtodolist.dao.jpa;

import com.menesates.noteandtodolist.dao.NoteRepository;
import com.menesates.noteandtodolist.exception.NoteNotFoundException;
import com.menesates.noteandtodolist.model.Note;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class NoteRepositoryJpaImpTest {

    @Autowired
    @Qualifier("noteRepositoryJpaImpl")
    private NoteRepository noteRepository;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testFinfAll(){
        List<Note> notes = noteRepository.findAll("user1");

        MatcherAssert.assertThat(notes.size(), Matchers.equalTo(3));
    }

    @Test
    public void testFindById(){
        Note note = noteRepository.findById(1L,"user1");
        MatcherAssert.assertThat(note.getUsername(),Matchers.equalTo("user1"));
    }

    @Test
    public void testFindByCategory(){
        List<Note> notes = noteRepository.findByCategory("user1","danger");
        MatcherAssert.assertThat(notes.size(),Matchers.equalTo(2));
        IntStream.range(0,notes.size()).forEach(i -> MatcherAssert.assertThat(notes.get(i).getUsername(),Matchers.equalTo("user1")));
    }

    @Test(expected = PersistenceException.class)
    public void testCreate(){
        Note note = new Note();
        note.setHeader("test create");
        note.setBody("test body");
        note.setUsername("user4");
        note.setCategory("danger");

        noteRepository.create(note);

        entityManager.flush();
    }

    @Test
    public void testUpdate(){
        Note note = new Note();
        note.setId(1L);
        note.setHeader("test update");
        note.setBody("test body");
        note.setUsername("user1");
        note.setCategory("danger");

        Note result = noteRepository.update(note);
        MatcherAssert.assertThat(note.getId(),Matchers.equalTo(result.getId()));
        MatcherAssert.assertThat(result.getHeader(), Matchers.equalTo("test update"));
    }

    @Test(expected = NoteNotFoundException.class)
    public void testDelete(){
        Note note = new Note();
        note.setHeader("test delete");
        note.setBody("test body");
        note.setUsername("user4");
        note.setCategory("danger");

        noteRepository.create(note);
        noteRepository.delete(note.getId(),note.getUsername());
        Note result = noteRepository.findById(note.getId(),"user4");
    }
}
