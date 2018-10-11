package com.menesates.noteandtodolist.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class NoteAndTodoServiceWithoutAuthTokenTests {

    @Autowired
    private NoteAndTodoService noteAndTodoService;

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testFindNotes(){
        noteAndTodoService.findNotes("user1");
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testFindNote(){
        noteAndTodoService.findNote(1L, "user1");
    }
}
