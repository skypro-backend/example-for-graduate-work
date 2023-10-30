package ru.skypro.homework.model;
import lombok.*;
import ru.skypro.homework.dto.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

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
      @Column (name = "id", nullable = false) // используется для определения соответствия между атрибутами
      // в классе сущности и полями в таблице данных
      @GeneratedValue (strategy = GenerationType.IDENTITY) // cтратегия генерации
      private Integer id;

      @Column (name = "email", nullable = false, length = 32)
      @Email
      private String email;

      @Column (name = "password")
      @NotNull
      @Size (min = 8)
      private String password;

      @Column (name = "first_name", length = 32)
      @NotBlank
      private String firstName;

      @Column (name = "last_name", length = 32)
      @NotBlank
      private String lastName;

      @Column (name = "phone")
      private String phone;

      @Column (name = "role")
      @Enumerated (EnumType.STRING) // значение роли будет храниться как строка
      private Role role;

      @OneToOne
      @JoinColumn (name = "image")
      private Image image;
}

