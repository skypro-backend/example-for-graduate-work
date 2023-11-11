package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name = "user_id")
    private long id;

    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
    private String regDate = String.valueOf(LocalDateTime.now());
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Ads> ads;

    @OneToMany(mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Comment> comments;
}
