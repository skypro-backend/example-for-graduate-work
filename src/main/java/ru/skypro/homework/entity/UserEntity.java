package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

import javax.persistence.GeneratedValue;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * UserEntity - сущность
 * <br><i>содержит следующие поля:</i>
 * <br>- id <i>(id пользователя)</i>;
 * <br>- email <i>(логин пользователя)</i>;
 * <br>- firstName <i>(имя пользователя)</i>;
 * <br>- lastName <i>(фамилия пользователя)</i>;
 * <br>- phone <i>(телефон пользователя)</i>;
 * <br>- role <i>(роль пользователя, {@link Role})</i>;
 * <br>- image <i>(аватар пользователя)</i>.
 * <br>- adsId <i>(объявления пользователя, {@link List<>})</i>;
 * <br>- commentsId <i>(комментарии пользователя, {@link List<>})</i>.
 */
@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)//GenerationType
    private Integer id;

    private String userName;//login-email

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private Role role;

    private String image;

//    private Long adId;// id объявления

    private List<?> adsId;//список  id_объявлений

//    private Long commentId;// id комментария

    private List<?> commentsId;//список  id_комментариев
}
