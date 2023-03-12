package ru.skypro.homework.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.skypro.homework.entity.AdEntity;

/**
 * A DTO for the {@link AdEntity} entity
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdsDTO {

  Integer author;
  String image;
  Integer pk;
  @NotNull
  @Min(value = 1)
  Integer price;
  @NotNull(message = "Обязательно нужно заполнить поле")
  String title;
}