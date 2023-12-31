package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

/**
 * <h2>AdsDto</h2>
 * DTO contained number of DTOs of advertisement (Ad entities) and list of advertisements (Ad entities)
 */

@Data
public class AdsDto {
    /**
     * Number of found advertisements
     */
    private Integer count;

    /**
     * List of found advertisements
     */
    private List<AdDto> results;
}
