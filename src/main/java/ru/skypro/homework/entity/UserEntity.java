package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate regDate;
    private String password;
    private String username;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<AdsEntity> adsList;
}
