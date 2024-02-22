package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

import javax.persistence.GeneratedValue;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * UserEntity - сущность
 * <br><i>содержит поля:</i>
 * <br>- id <i>(id пользователя)</i>;
 * <br>- email <i>(логин пользователя)</i>;
 * <br>- password <i>(пароль пользователя)</i>;
 * <br>- firstName <i>(имя пользователя)</i>;
 * <br>- lastName <i>(фамилия пользователя)</i>;
 * <br>- phone <i>(телефон пользователя)</i>;
 * <br>- role <i>(роль пользователя, {@link Role})</i>;
 * <br>- adsId <i>(объявления пользователя, {@link List<AdEntity>})</i>;
 * <br>- commentsId <i>(комментарии пользователя, {@link List<CommentEntity>})</i>.
 */
@Entity
@Data
@Table(name = "users")
public class UserEntity {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)//GenerationType
    private Long id;

    /**
     * логин пользователя
     */
    private String email;//login-email-username

    /**
     * пароль пользователя
     */
    private String password;

    /**
     * имя пользователя
     */
    private String firstName;

    /**
     * фамилия пользователя
     */
    private String lastName;

    /**
     * телефон пользователя
     */
    private String phone;

    /**
     * роль пользователя
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * связь {@link UserEntity} и {@link AdEntity}
     * <br><i>один пользователь - много объявлений</i>
     */
    @OneToMany(mappedBy = "author")
    private List<AdEntity> ads;// id объявления

    /**
     * связь {@link UserEntity} и {@link CommentEntity}
     * <br><i>один пользователь - много комментариев</i>
     */
    @OneToMany(mappedBy = "author")
    private List<CommentEntity> comments;// id комментария

    @OneToOne
    @JoinColumn(name = "image")
    private ImageEntity image;
}
