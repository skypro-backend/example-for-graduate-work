package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Comments {
    @Schema(description = "общее количество комментариев")
    Integer count;
    Comment results = new Comment();
}
