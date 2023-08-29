package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdsDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer pk;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer author;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String image;
    private String title;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String description;
    private int price;
}
