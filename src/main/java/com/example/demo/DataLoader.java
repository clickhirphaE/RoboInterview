package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JobPositionRepository jobPositionRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {

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
        JobPosition position = new JobPosition("McDonald", "Dishwasher", 20000.00,"Part-Time", "cleaning ,stocking", "nurse", "Gaithersburg, md","08/1/2019");
        jobPositionRepository.save(position);

        position = new JobPosition("Aldi", "Cashier", 14.30 , "Part-Time",
                " As a store employee, you're also responsible for merchandising product, monitoring inventory and keeping the store looking its best. " +
                        "It's an opportunity to get more out of your career and grow in an exciting environment.","cashier, register, products, customer service",
                "Gaithersburg, MD", "08/02/2019");

        jobPositionRepository.save(position);

    }
}

