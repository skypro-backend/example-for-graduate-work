package ru.skypro.homework.api.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Список комментариев")
public class Comments {

    @Schema(description = "общее количество комментариев")
    private Integer count = 0;

    @ArraySchema(schema = @Schema(implementation = Comment.class))
    private List<Comment> results = new ArrayList<>();
}
