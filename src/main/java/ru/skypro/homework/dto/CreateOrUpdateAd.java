package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class CreateOrUpdateAd {
    private String title;
    private Integer price;
    private String description;

    public CreateOrUpdateAd() {
    }
}
