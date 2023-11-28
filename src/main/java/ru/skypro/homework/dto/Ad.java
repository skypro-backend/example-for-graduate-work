package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

    private int author = 0;
    private String image = "string";
    private int pk = 0;
    private int price = 0;
    private String title = "string";

}
