package ru.skypro.homework.dto.model_dto;
import lombok.*;
import ru.skypro.homework.dto.Role;

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
      private String phone; //телефон пользователя
      private Role role;
      private String image; //ссылка на аватар пользователя
}
