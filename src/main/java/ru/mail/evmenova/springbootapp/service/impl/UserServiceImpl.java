package ru.mail.evmenova.springbootapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.mail.evmenova.springbootapp.exception.ApiException;
import ru.mail.evmenova.springbootapp.model.User;
import ru.mail.evmenova.springbootapp.repository.UserRepository;
import ru.mail.evmenova.springbootapp.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User prev = findById(user.getId());
        if (StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(prev.getPassword());
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllByOrderById();
    }

    @Override
    public void deleteById(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
