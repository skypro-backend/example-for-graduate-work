package ru.skypro.homework.dto.model_dto;
import lombok.Data;
import ru.skypro.homework.model.Ad;
import java.util.List;

/**
 * Класс DTO для передачи информации об объявлении
 */
@Data
public class AdsDto {
      private Integer count; // количество объявлений в списке
      private List <Ad> results; // Список объявлений

}
