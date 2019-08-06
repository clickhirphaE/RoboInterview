package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.utils.ObjectUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
    MultipartFile file) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("user", "User Account Created");
        }

        if (file.isEmpty()) {
            return "redirect:/register";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            user.setPic(uploadResult.get("url").toString());
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
//            return "redirect:/";
        }

        return "redirect:/home";
    }

    @RequestMapping("/")
    public String home() {


        //Default direct to login if not logged in, to index if are logged in
        if (userService.getUser() == null) {
            return "login";
        } else {
            return "redirect:/home";
        }
    }


    //Login in Mapping
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(){
        return "redirect:/home";
    }
}
