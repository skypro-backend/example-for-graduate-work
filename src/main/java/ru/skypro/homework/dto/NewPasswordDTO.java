package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * DTO пароля
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewPasswordDTO {

  /**
   * id пароля
   */
  @JsonIgnore
  private Long id;
  /**
   * текущий пароль
   */
  String currentPassword;
  /**
   * новый пароль
   */
  String newPassword;

}
