package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class RegisterDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private RoleDto role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterDto register = (RegisterDto) o;
        return Objects.equals(username, register.username) && Objects.equals(password, register.password) && Objects.equals(firstName, register.firstName) && Objects.equals(lastName, register.lastName) && Objects.equals(phone, register.phone) && role == register.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName, phone, role);
    }
}
