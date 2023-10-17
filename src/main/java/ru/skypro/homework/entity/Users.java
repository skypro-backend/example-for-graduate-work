package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.comment.CommentDTO;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
//этот класс станет entity
public class Users {
    private Integer id;
    private String email;
    private String image;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lot")
//    private List<Comment> commentList;

}