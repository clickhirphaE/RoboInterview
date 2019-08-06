package com.example.demo;



import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.HashSet;

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
        JobPosition position = new JobPosition("McDonald", "Dishwasher", 20000.00,"Part-Time", "nurse","Gaithersburg, md","08/1/2019");
        jobPositionRepository.save(position);

        position = new JobPosition("Aldi", "Cashier", 14.30 , "Part-Time",
                " As a store employee, you're also responsible for merchandising product, monitoring inventory and keeping the store looking its best. " +
                        "It's an opportunity to get more out of your career and grow in an exciting environment.",
                "Gaithersburg, MD", "08/02/2019");
        position.setKeywords("cashier");
        position.setKeywords("register");
        position.setKeywords("customer");
        jobPositionRepository.save(position);

        position = new JobPosition();


        position.setCompany("Google");
        position.setTitle("Software developer");
        position.setSalary(2000.00);
       position.setKeywords("java");
        position.setAddress("seattle washington");
        position.setJobType("full-time");
      position.setPosteddate("8/1/2019");
        position.setDescription("This position requires the skill of developing stuunning application");
        position.setKeywords("Java");
        position.setKeywords("programming");
        position.setKeywords("customer");
        jobPositionRepository.save(position);

    }
}

