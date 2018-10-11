package com.menesates.noteandtodolist.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class NoteAndTodoServiceWithAuthTokenTests {

    @Autowired
    private NoteAndTodoService noteAndTodoService;

    @Before
    public void setUp(){
        TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken(
                "userTest",
                "secret",
                "ROLE_NULL");
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @After
    public void tearDown(){
        SecurityContextHolder.clearContext();
    }


    @Test(expected = AccessDeniedException.class)
    public void testFindNotes(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        noteAndTodoService.findNotes(username);
    }

    @Test(expected = AccessDeniedException.class)
    public void testFindNoteById(){
        noteAndTodoService.findNote(1L);
    }
}
