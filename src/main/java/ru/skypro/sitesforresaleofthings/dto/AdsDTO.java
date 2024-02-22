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
public class AdsDTO {

    private Integer count;
    private List<AdDTO> results;
}