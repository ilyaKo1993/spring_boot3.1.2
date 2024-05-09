package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class MyInitClass {

    private final UserServiceImpl userService;

    public MyInitClass(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {

        Set<Role> adminRoles = new HashSet<>();
        Role userRole = new Role();
        userRole.setRole("USER");
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");

        adminRoles.add(userRole);
        adminRoles.add(adminRole);

        User admin = new User();
        admin.setLogin("admin");
        admin.setName("admin");
        admin.setLastName("admin");
        admin.setAge(18);
        admin.setRoles(adminRoles);
        admin.setPassword("$2a$12$a7gePTs3PFiG/b3x8ht.zuqTBUX2hQQ/dEn0XdiFO0zWG5R6GRY4u");

        userService.addUser(admin);
    }
}
