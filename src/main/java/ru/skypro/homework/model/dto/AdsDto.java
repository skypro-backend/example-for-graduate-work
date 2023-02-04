package ru.skypro.homework.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class AdsDto {
    @JsonProperty("author")
    private Integer author;
    @JsonProperty("image")
    private String image;
    @JsonProperty("pk")
    private Integer pk;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("title")
    private String title;
}
