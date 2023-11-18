package ru.skypro.homework.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ad {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;
}