package ru.mail.evmenova.springbootapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mail.evmenova.springbootapp.model.Role;
import ru.mail.evmenova.springbootapp.model.User;
import ru.mail.evmenova.springbootapp.repository.RoleRepository;
import ru.mail.evmenova.springbootapp.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class SeedingService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SeedingService(UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

        setUpData();
    }

    public void setUpData() {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");

        Role userRole = new Role();
        userRole.setName("USER");

        roleRepository.saveAll(Arrays.asList(adminRole, userRole));

        User admin = new User();
        admin.setAge(19);
        admin.setEmail("admin@mail.ru");
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setPassword(passwordEncoder.encode("1111"));
        admin.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(adminRole.getName()))));
        userRepository.save(admin);

        User user = new User();
        user.setAge(19);
        user.setEmail("user@mail.ru");
        user.setFirstName("user");
        user.setLastName("user");
        user.setPassword(passwordEncoder.encode("1111"));
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(userRole.getName()))));

        userRepository.save(user);
    }
}
