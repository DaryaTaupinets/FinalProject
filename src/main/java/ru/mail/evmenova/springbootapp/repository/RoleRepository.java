package ru.mail.evmenova.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import ru.mail.evmenova.springbootapp.exception.ApiException;
import ru.mail.evmenova.springbootapp.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String roleName);

    Optional<Role> findByNameIgnoreCase(String name);

    default Role findByNameOrThrow(String name) {
        return findByNameIgnoreCase(name)
                .orElseThrow(() -> new ApiException("Role with name: " + name, HttpStatus.NOT_FOUND));
    }
}
