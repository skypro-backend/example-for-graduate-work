package ru.skypro.homework.model.dto;

import lombok.Data;
import ru.skypro.homework.model.entity.Image;

import java.util.List;

@Data
public class AdsDto {
    private Integer author;
    private List<Image> image;
    private Integer pk;
    private Integer price;
    private String title;
}
