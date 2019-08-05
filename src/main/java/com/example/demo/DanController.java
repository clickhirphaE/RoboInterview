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
import java.time.format.DateTimeFormatter;

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

        if(user.getActiveResume() == null){
            user.setActiveResume(resume);
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


        //Adding interview questions

        Question question = new Question();
        question.setPrompt("What is your favorite color?");
        question.setInterview(interview);
        questionRepository.save(question);

        question = new Question();
        question.setPrompt("Where will you be in 5 years");
        question.setInterview(interview);
        questionRepository.save(question);

        question = new Question();
        question.setPrompt("Why do you want to work here?");
        question.setInterview(interview);
        questionRepository.save(question);

        //Start interview timer
//        interview.setStartTime(LocalDateTime.now());
//       char[] dateChar= interview.getDateEntry().toCharArray();
//       dateChar[10] = " ";

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        interview.setStartTime(LocalDateTime.parse(interview.getDateEntry(), formatter));
//        interview.setEndTime(LocalDateTime.now().plusHours(2));

        interview.setStatus("Submitted");

        interviewRepository.save(interview);
        return "redirect:/home";
    }
    //    Main Home Page
    @RequestMapping("/home")
    public String index(Model model){
        model.addAttribute("resumes", resumeRepository.findAll());
        model.addAttribute("interviews", interviewRepository.findAll());

        //Tracks interview Times
//        for (Interview interview:interviewRepository.findAll()) {
//
//            Duration duration = Duration.between(interview.getEndTime(),LocalDateTime.now());
//            double n = duration.getSeconds();
//
//            interview.setCheckTime((n/60));
//        }
        return "index";
    }

    @GetMapping("/interviewpopup")
    public String popup(Model model, Interview interview ){
        model.addAttribute("interview", interview);

        for(Question question : interview.getQuestions()){

            if(question.getAnswer().isEmpty()){
                model.addAttribute("question", question);
            }
        }

        return "interviewpopup";
    }

    @PostMapping("/processpopup")
    public String processpopup(Interview interview){
        for(Question question : interview.getQuestions()){
            if(question.getAnswer().isEmpty()){
                return "redirect:/interviewpopup";
            }
            else {questionRepository.save(question);}
            }

        return "redirect:/home";

    }

    @GetMapping("/resumeselection")
        public String resumeSelection(Model model){
            model.addAttribute("resumes", userService.getUser().getResumes());
            model.addAttribute("user", userService.getUser());

            return "resumeselection";
    }

    @PostMapping("/processselection")
        public String processSelectition(User user){

        userRepository.save(user);

        return "redirect:/home";
    }





}
