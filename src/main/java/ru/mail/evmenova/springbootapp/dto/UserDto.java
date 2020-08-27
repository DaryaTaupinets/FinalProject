package ru.mail.evmenova.springbootapp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

public class UserDto {
    private int id;
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotBlank(message = "Last Name  is mandatory")
    private String lastName;
    @Positive
    @NotNull(message = "Age is mandatory")
    private Integer age;
    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;
    private String password;
    private List<String> roles;
    private String rolesDisplayed;
    private boolean admin;

    public UserDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getRolesDisplayed() {
        return rolesDisplayed;
    }

    public void setRolesDisplayed(String rolesDisplayed) {
        this.rolesDisplayed = rolesDisplayed;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto dto = (UserDto) o;
        return id == dto.id &&
                Objects.equals(firstName, dto.firstName) &&
                Objects.equals(lastName, dto.lastName) &&
                Objects.equals(age, dto.age) &&
                Objects.equals(email, dto.email) &&
                Objects.equals(password, dto.password);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rolesDisplayed='" + rolesDisplayed + '\'' +
                '}';
    }
}
