package ru.skypro.homework.entity;

import lombok.*;
import org.hibernate.Hibernate;
import ru.skypro.homework.dto.Role;
import javax.persistence.*;

import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String phone;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;
    private String image;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
