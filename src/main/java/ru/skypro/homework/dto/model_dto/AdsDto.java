package ru.skypro.homework.dto.model_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Класс DTO для передачи информации об объявлении
 */
@Data
public class AdsDto {

      private Integer counts;
      private List<AdDto> results;
}
