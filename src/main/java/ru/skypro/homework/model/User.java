package ru.skypro.homework.model;
import lombok.*;
import ru.skypro.homework.dto.Role;
import javax.persistence.*;

/**
 * Класс пользователя системы онлайн-продаж.
 */
@Entity // сущность
@Table(name = "user_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

      @Id // помечает поле в классе модели как первичный ключ
      @Column(name = "id", nullable = false) // используется для определения соответствия между атрибутами
      // в классе сущности и полями в таблице данных
      @GeneratedValue(strategy = GenerationType.IDENTITY) // cтратегия генерации
      private Integer id;

      @Column(name = "email", nullable = false, length = 32)
      private String email;

      @Column(name = "password")
      private String password;

      @Column(name = "firstname", length = 32)
      private String firstName;

      @Column(name = "lastname", length = 32)
      private String lastName;

      @Column(name = "phone")
      private String phone;

      @Column(name = "role")
      @Enumerated(EnumType.STRING) // значение роли будет храниться как строка
      private Role role;

      @Column(name = "image")
      private String image;
}