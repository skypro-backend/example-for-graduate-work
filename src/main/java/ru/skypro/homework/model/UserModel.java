package ru.skypro.homework.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.skypro.homework.dto.enums.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private String firstName;
    private String lastName;
    private String phone;
    private String username;
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role")
    private Role role;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<AdsModel> ads;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<CommentModel> comments;

}
