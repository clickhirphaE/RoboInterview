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


////    Main Home Page
//    @RequestMapping("/home")
//    public String index(Model model){
//            model.addAttribute("resumes", resumeRepository.findAll());
//            model.addAttribute("interviews", interviewRepository.findAll());
//        return "index";
//    }



//    Contact Page
    @GetMapping("/contact")
    public String contactPage(){

        return "contact";
    }



//    Admin Settings Page
    @GetMapping("/admin")
    public String adminPage(){

        return "admin";
    }

}
