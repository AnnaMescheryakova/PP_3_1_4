package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void add(User user);

    User getUserById(int id);

    void update(User user, int id);

    void delete(int id);

    User findByUsername(String username);
}
