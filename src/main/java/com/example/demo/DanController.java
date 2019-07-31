package com.example.demo;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;

@Controller
public class DanController {
    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    UserService userService;

    //    New Resume Form
    @GetMapping("/resumeform")
    public String addResume( Model model){
        model.addAttribute("resume", new Resume());
        return "resumeform";
    }

    //    Processing New Resume Forms
    @PostMapping("/processresume")
    public String processResume(@Valid Resume resume){

        User user = userService.getUser();

        if(resume.getUser()==null){
            resume.setUser(user);
        }

        resume.setInfo(resume.toString(user));
        resumeRepository.save(resume);
        return "redirect:/home";
    }

    //    New Interview Form
    @GetMapping("/interviewform")
    public String addInterview(Model model){
        model.addAttribute("interview", new Interview());
        model.addAttribute("user_id", userService.getUser().getId());
        return "interviewform";
    }
    @PostMapping("/processinterview")
    public String processInterview(@Valid Interview interview){

        interview.setStartTime(LocalDateTime.now());
        interview.setEndTime(LocalDateTime.now().plusHours(2));
        interviewRepository.save(interview);
        return "redirect:/home";
    }
    //    Main Home Page
    @RequestMapping("/home")
    public String index(Model model){
        model.addAttribute("resumes", resumeRepository.findAll());
        model.addAttribute("interviews", interviewRepository.findAll());


        for (Interview interview:interviewRepository.findAll()) {

            Duration duration = Duration.between(interview.getEndTime(),LocalDateTime.now());
            double n = duration.getSeconds();

            interview.setCheckTime((n/60));
        }
        return "index";
    }
}
