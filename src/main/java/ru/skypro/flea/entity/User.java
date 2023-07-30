package ru.skypro.flea.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skypro.flea.model.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull
  @Column(name = "id")
  private Integer id;

  @Column(name = "first_name")
  @NotNull
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  @NotNull
  private String email;

  @Column(name = "password")
  @NotNull
  private String password;

  @Column(name = "phone")
  private String phone;

  @Column(name = "user_role")
  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(name = "image")
  private String image;
}
