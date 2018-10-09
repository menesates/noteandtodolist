package com.menesates.noteandtodolist.web;

import com.menesates.noteandtodolist.service.NoteAndTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {

    @Autowired
    private NoteAndTodoService noteAndTodoService;

    @RequestMapping("/notes")
    public ModelAndView getNotes(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("notes", noteAndTodoService.findNotes("user1"));
        modelAndView.setViewName("notes");
        return modelAndView;
    }


    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
