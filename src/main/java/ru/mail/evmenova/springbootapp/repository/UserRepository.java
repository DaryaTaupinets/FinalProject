package ru.mail.evmenova.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mail.evmenova.springbootapp.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByFirstName(String firstName);

    User findByEmail(String email);

    List<User> findAllByOrderById();
}
