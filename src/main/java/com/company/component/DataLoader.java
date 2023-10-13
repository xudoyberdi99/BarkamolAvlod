package com.company.component;

import com.company.entity.Admin;
import com.company.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String initialModeType;


    @Override
    public void run(String... args) throws Exception {
        if(initialModeType.equals("create")){
        adminRepository.save(new Admin(
                "Admin",
                passwordEncoder.encode("admin123")
        ));
        }
    }

}
