package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * Сущность Ads
 */
@Data
public class Ads {
    /**
     * Общее количество объявлений
     */


    @Schema(description = "общее количество объявлений")
   int count;
    /**
     * {@link Ad}
     */
    List<Ad> results;

}
