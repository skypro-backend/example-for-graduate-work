package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Краткая информация об объявлении.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ad {

    private Integer author;

    private String image;

    private Integer pk;

    private Integer price;

    private String title;
}
