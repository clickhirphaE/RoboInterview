package com.example.demo;


import com.sun.org.apache.xpath.internal.compiler.Keywords;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import sun.util.calendar.LocalGregorianCalendar;


import java.util.Arrays;
import java.util.HashSet;

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


//      Starting JobPositions

        JobPosition position = new JobPosition();


        position.setCompany("Google");
        position.setTitle("Software developer");
        position.setSalary(2000.00);
       position.setKeywords(Arrays.asList(""));
        position.setAddress("seattle washington");
        position.setJobType("full-time");
      position.setPosteddate();
        position.setDescription("This position requires the skill of developing stuunning application");


       //   position = new JobPosition("McDonald", "Dishwasher","customer service", 20000.00,"Part-time", "cleaning ,stocking", "nurse", "Gaithersburg, md","08/1/2019");

        jobPositionRepository.save(position);
    }
}
