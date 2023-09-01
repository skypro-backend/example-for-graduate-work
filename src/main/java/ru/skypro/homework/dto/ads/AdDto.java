package ru.skypro.homework.dto.ads;

import lombok.Data;

/**
 * DTO for {@link ru.skypro.homework.entity.ads.Ad}
 */
@Data
public class AdDto {
    private Integer pk;
    private String image;
    private Integer author;
    private String title;
    private Integer price;
}
