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

@Controller
public class HpJobPositionController {
    @Autowired
     JobPositionRepository jobPositionRepository;
    @Autowired
     UserService userService;
    //
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
        return "redirect:/";
    }
     //
    @RequestMapping("/processJobPosition")
    public String jobPositionDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("jobs",jobPositionRepository.findAll());
        if(userService.getUser()!=null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "processJobPosition";
    }
    @RequestMapping("/update/{id}")
    public String updateJobPosition(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobPositionRepository.findById(id).get());
        return "jobPositionForm";
    }
    @RequestMapping("/delete/{id}")
    public String delJobPosition(@PathVariable("id") long id){
        jobPositionRepository.deleteById(id);
        return "redirect:/";
    }





}
