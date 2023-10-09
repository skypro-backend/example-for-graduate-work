package ru.skypro.homework.dto.model_dto;
import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.validation.constraints.Pattern;

/**
 * Класс DTO для передачи полной информации о пользователе
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

      private Integer id; // идентификатор пользователя
      private String email;  //логин пользователя
      private String firstName;  //имя пользователя
      private String lastName; //фамилия пользователя
      @Pattern (regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
      private String phone; //телефон пользователя
      private Role role;
      private String image; //ссылка на аватар пользователя
}
