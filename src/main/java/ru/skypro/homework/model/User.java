package ru.skypro.homework.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * <h2>User entity</h2>
 * <br>
 * <b>Fields:</b><br>
 * private Long id;
 * private String name; 2-16 symbols<br>
 * private String surname; 2-16 symbols<br>
 * private String phoneNumber;<br>
 * private String email; <-- must be valid<br>
 * *     private Role userRole;<br>
 * private String idImage;<br>
 * private String password; 8-16 symbols <br>
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(min = 2, max = 10)
    private String name;

    @Column(name = "surname")
    @Size(min = 2, max = 16)
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    @Email(message = "Email must to be valid")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Column(name = "avatar")
    private String idImage;

    @Column(name = "password")
    @Size(min = 8, max = 16)
    private String password;




    public User(Long id, String name,
                String surname, String phoneNumber,
                String email, Role userRole,
                String idImage, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userRole = userRole;
        this.idImage = idImage;
        this.password = password;

    }
    /*

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String girl, String boy) {
        this.gender = girl;
        this.gender = boy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public int getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(int userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && userBirthday == user.userBirthday && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && userRole == user.userRole && Objects.equals(idImage, user.idImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phoneNumber, email, age, userRole, idImage, userBirthday);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", userRole=" + userRole +
                ", idImage='" + idImage + '\'' +
                ", userBirthday=" + userBirthday +
                '}';
    }*/
}
