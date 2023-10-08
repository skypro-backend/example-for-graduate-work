package ru.skypro.homework.model;

import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "first_name", nullable = false, length = 16)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 16)
    private String lastName;

    @Column(name = "phone", nullable = false, length = 30)
    private String phone;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "avatar_path")
    private String avatarFilePath;

    @Column(name = "avatar_size")
    private Integer avatarFileSize;

    @Column(name = "avatar_type")
    private String avatarMediaType;

    @Column(name = "pwd_hash", nullable = false)
    private String passwordHash;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getAvatarFilePath() {
        return avatarFilePath;
    }

    public void setAvatarFilePath(String avatarFilePath) {
        this.avatarFilePath = avatarFilePath;
    }

    public Integer getAvatarFileSize() {
        return avatarFileSize;
    }

    public void setAvatarFileSize(Integer avatarFileSize) {
        this.avatarFileSize = avatarFileSize;
    }

    public String getAvatarMediaType() {
        return avatarMediaType;
    }

    public void setAvatarMediaType(String avatarMediaType) {
        this.avatarMediaType = avatarMediaType;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(passwordHash, user.passwordHash) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, phone, email, passwordHash, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", role=" + role +
                '}';
    }
}
