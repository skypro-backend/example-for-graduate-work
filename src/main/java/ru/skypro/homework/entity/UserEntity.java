package ru.skypro.homework.entity;
import lombok.*;
import ru.skypro.homework.dto.Role;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

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
    private String password;
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "image_entity_path")
    private ImageEntity imageEntity;

    @OneToMany(mappedBy = "userEntity")
    private Collection<AdEntity> adEntity;

    @OneToMany(mappedBy = "userEntity")
    private List<CommentEntity> commentEntities;


}
