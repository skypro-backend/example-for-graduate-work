package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.models.Avatar;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.models.Item;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class UserDTO {
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
    private Avatar avatarId;
    @OneToMany
    private Collection<Item> itemId;
    @OneToMany
    private Collection<Comment> commentId;

    public UserDTO() {
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
