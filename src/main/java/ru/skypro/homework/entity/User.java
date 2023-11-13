package ru.skypro.homework.entity;

import lombok.*;
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
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String phone;
    @Getter
    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;
    private String image;

    public void setRole(final @NonNull Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final User user = (User) object;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(phone, user.phone) && role == user.role && Objects.equals(image, user.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, phone, role, image);
    }

}
