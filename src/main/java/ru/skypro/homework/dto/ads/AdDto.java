package ru.skypro.homework.dto.ads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {

    private int pk;
    private int author;
    private String image;
    private int price;
    private String title;
}
