package ru.skypro.homework.dto.model_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс DTO для передачи получения нового пользователе
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDto {

      private String currentPassword; // текущий пароль пользователя.
      private String newPassword; // новый пароль пользователя

}
