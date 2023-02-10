package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Min;
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

  @JsonIgnore
  Long id;

  @NotNull(message = "Обязательно нужно заполнить поле")
  @Size(message = "Длина не должна быть меньше 2 знаков и не больше 30", min = 2, max = 30)
  String image;

  @NotNull
  @Min(value = 1)
  int author;

  @NotNull
  @Min(value = 1)
  int price;

  @NotNull
  @Min(value = 1)
  int pk;

  @NotNull(message = "Обязательно нужно заполнить поле")
  @Size(message = "Длина не должна быть меньше 2 знаков и не больше 30", min = 2, max = 30)
  String title;

}
