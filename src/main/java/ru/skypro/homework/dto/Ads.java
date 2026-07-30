package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Список объявлений с общим количеством элементов.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ads {

    private Integer count;

    private List<Ad> results;
}
