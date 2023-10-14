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
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    @OneToMany(mappedBy = "userEntity")
    private Collection<AdEntity> adEntities;
    @OneToMany(mappedBy = "userEntity")
    private Collection<CommentEntity> commentEntities;
}
