package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    @Column(columnDefinition = "oid")
    private byte[] data;
    @OneToMany(mappedBy = "userEntity")
    private Collection<AdEntity> adEntities;
    @OneToMany(mappedBy = "userEntity")
    private Collection<CommentEntity> commentEntities;
}
