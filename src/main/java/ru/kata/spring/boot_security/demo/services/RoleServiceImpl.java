package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDAO;

    @Autowired
    public void setRoleDAO(RoleDao roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

    @Override
    @Transactional
    public void deleteRole(Role role) {
        roleDAO.deleteRole(role);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        roleDAO.updateRole(role);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }
}
