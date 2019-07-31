package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JobPositionRepository jobPositionRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception{

//        Starting Users
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("Jim@jim.com", "password", "Jim", "Jimmerson", "5555555555", true, "jimmy");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin", "User", "6666666666", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);


//        Starting JobPositions

        JobPosition position = new JobPosition("McDonald", "Dishwasher", 20000.00,"Part-time", "cleaning ,stocking", "nurse", "Gaithersburg,md");
        jobPositionRepository.save(position);
    }
}
