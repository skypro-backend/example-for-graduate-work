package ru.skypro.homework.dto.model_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс DTO для передачи информации при создании или обновлении объявления
 */
@Data
public class CreateAdsDto {

      private String title;  //заголовок объявления
      private String description; // описание объявления
      private Integer price; //цена объявления
}
