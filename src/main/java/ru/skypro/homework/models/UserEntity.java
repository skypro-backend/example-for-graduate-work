package ru.skypro.homework.models;

import lombok.Data;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    @OneToOne
    private AvatarEntity avatarId;
    @OneToMany
    private Collection<ItemEntity> itemId;
    @OneToMany
    private Collection<CommentEntity> commentId;

    public UserEntity() {
    }

    public void setName(Register register) {
        this.username = register.getUsername();
    }

    public void setPassword(Register register) {
        this.password = register.getPassword();
    }

    public void setFirsName(Register register) {
        this.firstName = register.getFirstName();
    }

    public void setLastName(Register register) {
        this.lastName = register.getLastName();
    }

    public void setPhone(Register register) {
        this.phone = register.getPhone();
    }

    public void setRole(Register register) {
        this.role = register.getRole();
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }
}
