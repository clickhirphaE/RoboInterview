package com.example.demo;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

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

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    JobPositionRepository jobPositionRepository;

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

        user.setActiveResume(resume.getTitle());
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

        User user = userService.getUser();

        if(interview.getUser()==null){
            interview.setUser(user);
        }

        //Adding interview questions

        Question question = new Question();
        question.setPrompt("What is your favorite color?");
        question.setInterview(interview);
        questionRepository.save(question);
        interview.setQuestions(question);
        interviewRepository.save(interview);

        question = new Question();
        question.setPrompt("Where will you be in 5 years");
        question.setInterview(interview);
        questionRepository.save(question);
        interview.setQuestions(question);
        interviewRepository.save(interview);

        question = new Question();
        question.setPrompt("Why do you want to work here?");
        question.setInterview(interview);
        questionRepository.save(question);
        interview.setQuestions(question);
        interviewRepository.save(interview);

        //Start interview timer
//        interview.setStartTime(LocalDateTime.now());
//       char[] dateChar= interview.getDateEntry().toCharArray();
//       dateChar[10] = " ";

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        interview.setStartTime(LocalDateTime.parse(interview.getDateEntry(), formatter));
//        interview.setEndTime(LocalDateTime.now().plusHours(2));

        interview.setStatus("Submitted");

//


        interview.setResume(userService.getUser().getActiveResume());
        interviewRepository.save(interview);

        return "redirect:/home";

    }


    //    Main Home Page
    @RequestMapping("/home")
    public String index(Model model){
        model.addAttribute("resumes", resumeRepository.findAll());
        model.addAttribute("interviews", userService.getUser().getInterviews());
        model.addAttribute("jobpositions",jobPositionRepository.findAll());
        model.addAttribute("user_id", userService.getUser());

        if (userService.getUser() == null) {
            return "login";
        } else {
            return "index";
        }

        //Tracks interview Times
//        for (Interview interview:interviewRepository.findAll()) {
//
//            Duration duration = Duration.between(interview.getEndTime(),LocalDateTime.now());
//            double n = duration.getSeconds();
//
//            interview.setCheckTime((n/60));
//        }
    }

    @GetMapping("/interviewpopup/{id}")
    public String popup(@PathVariable("id") long id, Model model){

        Interview interview = interviewRepository.findById(id);

//        for (Question q : interview.getQuestions()){
//
//        }

        model.addAttribute("interview", interview);
//        model.addAttribute("questions", interview.getQuestions());


        return "interviewpopup";
    }

    @PostMapping("/processpopup")
    public String processpopup(@ModelAttribute Interview interview) {

        interview.setStatus("Under Review");
        for(Question question : interview.getQuestions()){
            System.out.println(question.getAnswer());
            questionRepository.save(question);
        }


        interviewRepository.save(interview)    ;
            return "redirect:/home";
        }



    @GetMapping("/resumeselection")
        public String resumeSelection(Model model){
            model.addAttribute("resumes", userService.getUser().getResumes());
            model.addAttribute("user", userService.getUser());

            return "resumeselection";
    }

    @PostMapping("/processselection")
        public String processSelection(User user){

        userRepository.save(user);

        return "redirect:/home";
    }





}
