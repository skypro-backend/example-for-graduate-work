package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @Column(name = "phone_number")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String imageUrl;

    @OneToMany(mappedBy = "author", fetch=FetchType.LAZY)
    private java.util.List<Ad> ads;

    @OneToMany(mappedBy = "author", fetch=FetchType.LAZY)
    private List<Comment> comments;

   @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;
    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.email = userDto.getEmail();
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.phone = userDto.getPhone();
        this.role = userDto.getRole();
         }
}


