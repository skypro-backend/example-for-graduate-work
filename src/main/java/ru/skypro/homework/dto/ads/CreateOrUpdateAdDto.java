package ru.skypro.homework.dto.ads;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrUpdateAdDto {

    private String title;
    private int price;
    private String description;
}
