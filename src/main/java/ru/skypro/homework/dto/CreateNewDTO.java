package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewDTO {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Price")
    private Integer price;

    @JsonProperty("Description")
    private String description;


}
