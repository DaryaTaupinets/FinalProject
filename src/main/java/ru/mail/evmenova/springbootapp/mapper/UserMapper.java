package ru.mail.evmenova.springbootapp.mapper;

import ru.mail.evmenova.springbootapp.dto.UserDto;
import ru.mail.evmenova.springbootapp.model.Role;
import ru.mail.evmenova.springbootapp.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper() {
    }

    public static User toModel(UserDto dto){
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static List<UserDto> toDto(List<User> userList) {
        return userList.stream().map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAge(user.getAge());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRolesDisplayed(user.getRoles().stream().map(Role::getName).collect(Collectors.joining(" ")));
        dto.setAdmin(user.getRoles().stream()
                .anyMatch(role -> "ADMIN".equalsIgnoreCase(role.getName())));
        return dto;
    }
}
