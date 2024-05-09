package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {

    void addRole(Role role);

    void deleteRole(Role role);

    void updateRole(Role role);

    Role getRoleById(Long id);

    List<Role> getAllRoles();

    Role getRoleByName(String name);
}
