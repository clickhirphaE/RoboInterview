package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class HpJobPositionController {

    @Autowired
     JobPositionRepository jobPositionRepository;
    @Autowired
     UserService userService;
    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/jobPositionForm")
    public String addJobPosition(Model model){
        model.addAttribute("job", new JobPosition());
        return "jobPositionForm";
    }
    @PostMapping("/processJobPosition")
    public String processJobPosition(@Valid JobPosition jobPosition, BindingResult result){

        if(result.hasErrors()){
            return "jobPositionForm";
        }
        jobPositionRepository.save(jobPosition);
        return "redirect:/home";
    }
     //
    @RequestMapping("/apply/{id}")
    public String jobPositionDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobPositionRepository.findById(id));
        if(userService.getUser()!=null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }

        return "processJobPosition";
    }
    @RequestMapping("/update/{id}")
    public String updateJobPosition(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobPositionRepository.findById(id));
        return "jobPositionForm";
    }
    @RequestMapping("/delete/{id}")
    public String delJobPosition(@PathVariable("id") long id){
        jobPositionRepository.deleteById(id);
        return "redirect:/home";
    }
    @GetMapping("/profile")
    public String profile(Model model){

        return "profile";
    }

    @RequestMapping("/finalapply/{id}")
    public String apply(@PathVariable("id") long id, Model model){
        ArrayList<String> test = new ArrayList<>();
        model.addAttribute("jobPosition", jobPositionRepository.findById(id));
        for(String word: userService.getUser().getActiveResume().split(" ")) {
            test.add(word);
        }

        JobPosition jobPosition =jobPositionRepository.findById(id);
        double i =0;
        for(String word : jobPosition.getKeywords()){
            if(test.contains(word)){
                i++;
            }
        }
        if((jobPosition.getKeywords().size() / i) >= .8){
            Interview interview = new Interview();
            interview.setJobPosition(jobPositionRepository.findById(id));
            interview.setUser(userService.getUser());

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


            model.addAttribute("interview", interview);
            return "interviewform";
        }
        else {
             Interview interview = new Interview();
            interview.setStatus("Rejected");
            interview.setUser(userService.getUser());
            interview.setJobPosition(jobPositionRepository.findById(id));
            interviewRepository.save(interview);
            return "redirect:/home";
        }
    }



}
