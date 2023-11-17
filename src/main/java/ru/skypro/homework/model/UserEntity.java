package ru.skypro.homework.model;

import lombok.*;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
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

    @OneToOne
    private AvatarEntity avatar;

    @OneToMany(mappedBy = "author")
    private Collection<AdEntity> ads;

    @OneToMany(mappedBy = "author")
    private Collection<CommentEntity> comments;


    public void setName(Register register) {
        this.username = register.getUsername();
    }

    public void setPassword(Register register) {
        this.password = register.getPassword();
    }
    public void setPassword(String password){
        this.password = password;
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

    public void setAllFields(Register register){
        setName(register);
        setPassword(register);
        setFirsName(register);
        setLastName(register);
        setPhone(register);
        setRole(register);
    }

}
