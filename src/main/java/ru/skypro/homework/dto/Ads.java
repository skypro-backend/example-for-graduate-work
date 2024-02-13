package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.links.Link;
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
    private int count;
    /**
     * {@link Ad}
     */
    private List<Ad> results;

}
