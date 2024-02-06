package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.constans.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private PhotoEntity photo;

    @OneToMany(mappedBy = "author")
    private Collection<AdEntity> ads;

    @OneToMany(mappedBy = "author")
    private Collection<CommentEntity> comments;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;


}
