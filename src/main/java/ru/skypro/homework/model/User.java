package ru.skypro.homework.model;
import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;


/**
 * Класс пользователя системы онлайн-продаж.
 */
@Entity // сущность
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

      @Id // помечает поле в классе модели как первичный ключ
      @Column(name = "id", nullable = false) // используется для определения соответствия между атрибутами
      // в классе сущности и полями в таблице данных
      @GeneratedValue(strategy = GenerationType.IDENTITY) // cтратегия генерации
      private Integer id;

      @Column(name = "firstName")
      private String firstName;

      @Column(name = "lastName")
      private String lastName;

      /**
       * Номер телефона пользователя в формате +7 (XXX) XXX-XX-XX.
       */
      //@Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
      @Column(name = "phone")
      private String phone;

      @Column(name = "email")
      private String email;

      @Column(name = "password")
      private String password;

      @JoinColumn(name = "image")
      private String image;

      @Column(name = "role")
      @Enumerated(EnumType.STRING) // значение роли будет храниться как строка
      private Role role;

}
