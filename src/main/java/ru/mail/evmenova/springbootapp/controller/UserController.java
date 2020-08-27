package ru.mail.evmenova.springbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.evmenova.springbootapp.dto.UserDto;
import ru.mail.evmenova.springbootapp.mapper.UserMapper;
import ru.mail.evmenova.springbootapp.model.User;
import ru.mail.evmenova.springbootapp.service.RoleService;
import ru.mail.evmenova.springbootapp.service.impl.RoleServiceImpl;
import ru.mail.evmenova.springbootapp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto, Authentication authentication) {
        User user = UserMapper.toModel(userDto);
        user.setRoles(roleService.mapRoleNamesToRoles(userDto.getRoles()));
        return ResponseEntity.ok(UserMapper.toDto(userService.create(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") int userId,
            @RequestBody @Valid UserDto userDto,
            Authentication authentication) {
        User user = UserMapper.toModel(userDto);
        user.setId(userId);
        user.setRoles(roleService.mapRoleNamesToRoles(userDto.getRoles()));

        return ResponseEntity.ok(UserMapper.toDto(userService.update(user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int userId, Authentication authentication) {
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable("id") int userId, Authentication authentication) {
        return ResponseEntity.ok(UserMapper.toDto(userService.findById(userId)));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok(UserMapper.toDto(userService.findByEmail(authentication.getName())));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(Authentication authentication) {
        return ResponseEntity.ok(UserMapper.toDto(userService.findAll()));
    }
}
