package ru.skypro.homework.entity;

import lombok.*;
import org.hibernate.Hibernate;
import ru.skypro.homework.dto.Role;
import jakarta.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    String email;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    String phone;
    @NonNull
    Role role;
    @NonNull
    String image;


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
