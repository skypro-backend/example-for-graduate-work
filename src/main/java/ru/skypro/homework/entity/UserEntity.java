package ru.skypro.homework.entity;
import lombok.*;
import ru.skypro.homework.dto.Role;
import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_entity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne
    @JoinColumn(name = "image_entity_path")
    private ImageEntity imageEntity;

    @OneToMany(mappedBy = "ad_entity_id")
    private Collection<AdEntity> adEntity;

    @OneToMany(mappedBy = "userEntity")
    private Collection<CommentEntity> commentEntities;



}
