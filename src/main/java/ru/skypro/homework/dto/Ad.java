package ru.skypro.homework.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;

}
