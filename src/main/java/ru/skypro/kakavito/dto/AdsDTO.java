package ru.skypro.kakavito.dto;

import lombok.Data;

import java.util.List;

/**
 * Создание ДТО
 */
@Data
public class AdsDTO {
    private int count;
    private List<AdDTO> results;
}
