package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class CreateOrUpdateAd {
    private String title; //minLength: 4, maxLength: 32
    private Integer price; //minimum: 8, maximum: 10000000
    private String description; //minLength: 8, maxLength: 64
}
