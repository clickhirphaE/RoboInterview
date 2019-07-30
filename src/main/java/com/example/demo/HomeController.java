package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    JobPositionRepository jobPositionRepository;


//    Main Home Page
    @RequestMapping("/home")
    public String index(){

        return "index";
    }

//    New Resume Form
    @GetMapping("/resumeform")
    public String addResume(Model model){
        model.addAttribute("resume", new Resume());

        return "resumeform";
    }

//    Processing New Resume Forms
    @PostMapping("/processresume")
    public String processResume(){

        return "redirect:/home";
    }

//    Contact Page
    @GetMapping("/contact")
    public String contactPage(){

        return "contact";
    }

//    New Interview Form
    @GetMapping("/interviewform")
    public String addInterview(Model model){
        model.addAttribute("interview", new Interview());

        return "interviewform";
    }

//    Admin Settings Page
    @GetMapping("/admin")
    public String adminPage(){

        return "admin";
    }

}
