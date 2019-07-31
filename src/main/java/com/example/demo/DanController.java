package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DanController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    UserService userService;

    //    New Resume Form
    @GetMapping("/resumeform")
    public String addResume(User user, Resume resume, Model model){
        model.addAttribute("resume", new Resume());
        model.addAttribute("user", userService.getUser());
        return "resumeform";
    }

    //    Processing New Resume Forms
    @PostMapping("/processresume")
    public String processResume(@Valid Resume resume, User user){

        user = userService.getUser();
        resume.setInfo(resume.toString(user));
        resumeRepository.save(resume);
        return "redirect:/home";
    }
}
