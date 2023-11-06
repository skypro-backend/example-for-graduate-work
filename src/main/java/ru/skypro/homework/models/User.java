package ru.skypro.homework.models;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"comments", "image", "ads"})
@ToString(exclude = {"comments", "image", "ads"})
@Getter
@Setter
@Table(name = "users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @OneToMany(mappedBy = "user")
    private List<Ad> ads;
}
