package ru.skypro.homework.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class Properties {

  @NotNull(message = "Обязательно нужно заполнить поле")
  @Size(message = "Длина не должна быть меньше 2 знаков и не больше 30", min = 2, max = 30)
  String description;

  @NotNull(message = "Обязательно нужно заполнить поле")
  Integer price;

  @NotNull(message = "Обязательно нужно заполнить поле")
  @Size(message = "Длина не должна быть меньше 2 знаков и не больше 30", min = 2, max = 30)
  String title;


}
