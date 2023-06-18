package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseWrapperComment {
    @Schema(description = "общее количество комментариев")
    private Integer count;
    private ArrayList<Comment> results;
}
