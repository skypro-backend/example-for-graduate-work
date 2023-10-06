package ru.skypro.homework.projection;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class AdView {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
