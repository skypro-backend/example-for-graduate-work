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
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
    @OneToMany(mappedBy = "user_entity")
    private Collection<AdEntity> adEntities;
    @OneToMany(mappedBy = "user_entity")
    private Collection<CommentEntity> commentEntities;
}
