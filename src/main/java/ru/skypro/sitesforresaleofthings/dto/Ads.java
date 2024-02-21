package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

import java.util.List;

/**
 * DTO объявлений
 */

/**
 * Свойства:
 * 1) count - общее количество объявлений,
 * 2) results - список объявлений
 */
@Data
public class Ads {

    private Integer count;
    private List<Ad> results;
}