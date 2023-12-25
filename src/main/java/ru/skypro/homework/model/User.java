package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.awt.*;

//@Data
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
}
