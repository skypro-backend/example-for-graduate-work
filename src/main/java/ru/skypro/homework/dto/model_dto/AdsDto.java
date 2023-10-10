package ru.skypro.homework.dto.model_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Класс DTO для передачи информации об общем количестве объявлений
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdsDto {

      private Integer count; // количество объявлений в списке
      private List<AdDto> results; // список объявлений
}
