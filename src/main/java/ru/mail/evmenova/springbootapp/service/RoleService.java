package ru.mail.evmenova.springbootapp.service;

import ru.mail.evmenova.springbootapp.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> mapRoleNamesToRoles(List<String> rolesNames);
}
