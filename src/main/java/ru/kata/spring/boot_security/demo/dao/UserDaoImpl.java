package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();

    }

    @Override
    public User getUserById(long id) {
        Query query = entityManager.createQuery("FROM User usr WHERE usr.id = ?1");
        query.setParameter(1, id);
        return (User) query.getSingleResult();
    }

    @Override
    public void removeUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public User getUserByLogin(String name) {
        Query query = entityManager.createQuery("FROM User usr WHERE usr.login = ?1");
        query.setParameter(1, name);
        return (User) query.getSingleResult();
    }
}
