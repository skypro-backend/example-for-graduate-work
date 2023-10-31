package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "users")
public class UserEntity {

    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    @OneToOne
    @JoinColumn(name = "USER_IMAGE", nullable = true)
    private Image imageAvatar;
    private String username;
    private String password;

    @OneToMany(mappedBy = "userRelated",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    List<AdEntity> ads = new ArrayList<>();

    @OneToMany(mappedBy = "userRelated",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    List<AdEntity> comments = new ArrayList<>();


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
