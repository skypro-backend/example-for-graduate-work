package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.pojo.Image;

@Data
public class AllAdDTO {

    private Long author;
    private String image;
    private Long pk;
    private Long price;
    private String title;

}
