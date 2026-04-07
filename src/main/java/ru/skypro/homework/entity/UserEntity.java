package ru.skypro.homework.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String phone;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private RoleEnum role;

    private String image;
    private  String password;

    @Column(nullable = false)
    private Boolean enabled = true;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<AdvertisementEntity> ads;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
