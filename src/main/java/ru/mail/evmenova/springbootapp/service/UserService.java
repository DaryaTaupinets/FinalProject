package ru.mail.evmenova.springbootapp.service;

import ru.mail.evmenova.springbootapp.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user);

    User findById(Integer id);

    List<User> findAll();

    void deleteById(Integer id);

    User findByEmail(String firstName);
}
