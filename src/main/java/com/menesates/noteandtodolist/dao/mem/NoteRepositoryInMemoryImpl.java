package com.menesates.noteandtodolist.dao.mem;

import com.menesates.noteandtodolist.dao.NoteRepository;
import com.menesates.noteandtodolist.model.Note;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Bu sınıf NoteRepository arayüzünü implements ederek Note modeli ve repositorynin testlerini
 * veri tabanı kullanmadan bellekte yapabilmek için yazılmıştır.
 *
 * @author menesates
 * @since 2018-10-09
 */
@Repository
public class NoteRepositoryInMemoryImpl implements NoteRepository {

    private Map<Long, Note> noteMap = new HashMap<>();

    public NoteRepositoryInMemoryImpl() {
        Note note1 = new Note();
        note1.setId(1L);
        note1.setHeader("Note 1 Header");
        note1.setBody("Note 1 Body");
        note1.setUsername("user1");
        note1.setCategory("danger");

        Note note2 = new Note();
        note2.setId(2L);
        note2.setHeader("Note 2 Header");
        note2.setBody("Note 2 Body");
        note2.setUsername("user1");
        note2.setCategory("info");

        Note note3 = new Note();
        note3.setId(3L);
        note3.setHeader("Note 3 Header");
        note3.setBody("Note 3 Body");
        note3.setUsername("user2");
        note3.setCategory("warning");

        Note note4 = new Note();
        note4.setId(4L);
        note4.setHeader("Note 4 Header");
        note4.setBody("Note 4 Body");
        note4.setUsername("user2");
        note4.setCategory("succes");

        Note note5 = new Note();
        note5.setId(5L);
        note5.setHeader("Note 5 Header");
        note5.setBody("Note 5 Body");
        note5.setUsername("user1");
        note5.setCategory("danger");

        noteMap.put(note1.getId(),note1);
        noteMap.put(note2.getId(),note2);
        noteMap.put(note3.getId(),note3);
        noteMap.put(note4.getId(),note4);
        noteMap.put(note5.getId(),note5);

    }

    @Override
    public List<Note> findAll(String username) {
        return noteMap.values().stream()
                .filter(o -> o.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public Note findById(Long id) {
        return noteMap.get(id);
    }

    @Override
    public List<Note> findByCategory(String username, String category) {
        return noteMap.values().stream()
                .filter(o -> o.getUsername().equals(username) && o.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public void create(Note note) {
        //note.setId(new Date().getTime());
        noteMap.put(note.getId(),note);
    }

    @Override
    public Note update(Note note) {
        noteMap.replace(note.getId(),note);
        return note;
    }

    @Override
    public void delete(Long id) {
        noteMap.remove(id);
    }
}
