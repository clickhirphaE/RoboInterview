package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class JobPositionController {
    @Autowired
    JobPositionRepository jobPositionRepository;
    @Autowired
     UserService userService;
    //
    @RequestMapping("/processJobPosition")
    public String addJobPosition(Model model){
        model.addAttribute("job", new JobPosition("Google","sw developer",67000.00,"part-time","coding for 8 hours","software dev","seattle washington"));
        return "jobPositionForm";
    }
    @PostMapping("/processJobPosition")
    public String processJobPosition(@Valid JobPosition jobPosition, BindingResult result){
        if(result.hasErrors()){
            return "jobPositonForm";
        }
        jobPositionRepository.save(jobPosition);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String jobPositionDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("job",jobPositionRepository.findById(id).get());
        if(userService.getUser()!=null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateJobPosition(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobPositionRepository.findById(id).get());
        return "jobPositonForm";
    }
    @RequestMapping("/delete/{id}")
    public String delJobPosition(@PathVariable("id") long id){
        jobPositionRepository.deleteById(id);
        return "redirect:/";
    }




}
