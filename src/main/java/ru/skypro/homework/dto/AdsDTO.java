package ru.skypro.homework.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdsDTO {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;


}
