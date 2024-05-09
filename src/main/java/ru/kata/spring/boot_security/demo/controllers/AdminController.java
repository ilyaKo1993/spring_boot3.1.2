package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

    private UserServiceImpl userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public void setUserService(UserServiceImpl userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUser", userService.listUsers());
        return "users";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, @RequestParam("role") String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.removeUser((long) id);
        return "redirect:/admin";
    }

    @RequestMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("role") String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
