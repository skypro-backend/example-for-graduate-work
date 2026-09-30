package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "List of comments")
public class Comments {

    @Schema(description = "Number of comments")
    private Integer count;

    @Schema(description = "Comments")
    private List<Comment> results;
}