package ru.skypro.homework.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateOrUpdateAd {
    private String title;
    private int price;
    private String description;
}