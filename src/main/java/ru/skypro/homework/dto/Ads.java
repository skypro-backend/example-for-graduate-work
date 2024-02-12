package ru.skypro.homework.dto;

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
    private int count;
    /**
     * {@link Ad}
     */
    private List<Ad> results;

}
