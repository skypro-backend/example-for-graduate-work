package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "reg_date")
    private LocalDate regDate;
    @OneToOne
    @JoinColumn(name = "id")
    private AvatarEntity avatar;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<AdsEntity> adsList;

    public UserEntity(Integer id, String email, String firstName, String lastName, String phone, LocalDate regDate, AvatarEntity avatar, String username, String password, List<AdsEntity> adsList) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.regDate = regDate;
        this.avatar = avatar;
        this.username = username;
        this.password = password;
        this.adsList = adsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) && Objects.equals(email, that.email) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phone, that.phone) && Objects.equals(regDate, that.regDate) && Objects.equals(password, that.password) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, phone, regDate, password, username);
    }
}
