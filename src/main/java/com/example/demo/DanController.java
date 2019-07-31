package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DanController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    //    New Resume Form
    @GetMapping("/resumeform")
    public String addResume(Model model){
        model.addAttribute("resume", new Resume());
        model.addAttribute("user", userService.getUser());
        return "resumeform";
    }
}
