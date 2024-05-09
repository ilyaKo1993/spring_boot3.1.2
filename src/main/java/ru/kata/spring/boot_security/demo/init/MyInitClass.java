package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${app.db.default.admin.name}")
    private String name;

    @Value("${app.db.default.admin.last_name}")
    private String lastName;

    @Value("${app.db.default.admin.login}")
    private String login;

    @Value("${app.db.default.admin.password}")
    private String password;

    @Value("${app.db.default.admin.role_admin}")
    private String adminRoleValue;

    @Value("${app.db.default.user.role_user}")
    private String userRoleValue;

    @Value("${app.db.default.admin.age}")
    private int age;

    public MyInitClass(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {

        Set<Role> adminRoles = new HashSet<>();
        Role userRole = new Role();
        userRole.setRole(userRoleValue);
        Role adminRole = new Role();
        adminRole.setRole(adminRoleValue);

        adminRoles.add(userRole);
        adminRoles.add(adminRole);

        User admin = new User();
        admin.setLogin(login);
        admin.setName(name);
        admin.setLastName(lastName);
        admin.setAge(age);
        admin.setRoles(adminRoles);
        admin.setPassword(password);

        userService.addUser(admin);
    }
}
