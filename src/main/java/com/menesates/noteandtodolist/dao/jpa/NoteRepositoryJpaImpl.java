package com.menesates.noteandtodolist.dao.jpa;

import com.menesates.noteandtodolist.dao.NoteRepository;
import com.menesates.noteandtodolist.model.Note;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("noteRepositoryJpaImpl")
public class NoteRepositoryJpaImpl implements NoteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Note> findAll(String username) {
        String sql = "from Note where username = :username";
        return entityManager.createQuery(sql, Note.class)
                .setParameter("username",username).getResultList();
    }

    @Override
    public Note findById(Long id) {
        return entityManager.find(Note.class,id);
    }

    @Override
    public List<Note> findByCategory(String username, String category) {
        String sql = "from Note where username = :username and category = :category";
        return entityManager.createQuery(sql,Note.class)
                .setParameter("username",username)
                .setParameter("category",category)
                .getResultList();
    }

    @Override
    public void create(Note note) {
        entityManager.persist(note);
    }

    @Override
    public Note update(Note note) {
        return entityManager.merge(note);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Note.class,id));
    }
}
