package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseWrapperAds {
    @Schema(description = "общее количество объявлений")
    private Integer count;
    private ArrayList<Ads> results;
}
