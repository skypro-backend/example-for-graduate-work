package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;

import javax.persistence.*;
import java.awt.*;

@Data
//@Entity
//@Table(name = "users")
public class User {
    //  @Id
    //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //  @Column(unique = true, nullable = false)
    private String email;

    // @Column(nullable = false)
    private String password;

    // @Column(nullable = false)
    private String firstName;

    //  @Column(nullable = false)
    private String lastName;

    // @Column(nullable = false)
    private String phone;

    //  @Enumerated(EnumType.STRING)
    //  @Column(nullable = false)
    private Role role;

  /*  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Ad> ads;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments;*/

  /*  @OneToOne
    @JsonBackReference
    @JoinColumn(name = "image_id")
    private Image image;*/

    public User(Integer id, String email, String password, String firstName, String lastName, String phone, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.email = userDto.getEmail();
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.phone = userDto.getPhone();
        this.role = userDto.getRole();
    }
}


