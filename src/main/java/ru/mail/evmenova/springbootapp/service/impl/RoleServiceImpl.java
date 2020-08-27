package ru.mail.evmenova.springbootapp.service.impl;

import org.springframework.stereotype.Service;
import ru.mail.evmenova.springbootapp.model.Role;
import ru.mail.evmenova.springbootapp.repository.RoleRepository;
import ru.mail.evmenova.springbootapp.service.RoleService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> mapRoleNamesToRoles(List<String> rolesNames) {
        if (rolesNames != null && !rolesNames.isEmpty()) {
            return rolesNames.stream()
                    .map(roleRepository::findByNameOrThrow)
                    .collect(Collectors.toSet());
        }
        else {
            return new HashSet<>(Collections.singletonList(roleRepository.findByNameOrThrow("USER")));
        }
    }
}
