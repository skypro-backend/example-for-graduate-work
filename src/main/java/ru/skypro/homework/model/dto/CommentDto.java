package ru.skypro.homework.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentDto {
    @JsonProperty("author")
    private Integer author;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("pk")
    private Integer pk;
    @JsonProperty("text")
    private String text;
}
