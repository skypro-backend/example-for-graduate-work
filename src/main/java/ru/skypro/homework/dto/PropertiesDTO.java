package ru.skypro.homework.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Класс сущности "properties" в таблице
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertiesDTO {

  @NotNull(message = "Обязательно нужно заполнить поле")
  @Size(message = "Длина не должна быть меньше 2 знаков и не больше 30", min = 2, max = 30)
  String description;

  @NotNull(message = "Обязательно нужно заполнить поле")
  int price;

  @NotNull(message = "Обязательно нужно заполнить поле")
  @Size(message = "Длина не должна быть меньше 2 знаков и не больше 30", min = 2, max = 30)
  String title;


}
