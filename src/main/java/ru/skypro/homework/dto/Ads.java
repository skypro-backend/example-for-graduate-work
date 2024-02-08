package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

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
