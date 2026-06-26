package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "Список комментариев")
public class Comments {
    @Schema(description = "Общее количество комментариев")
    private Integer count;

    @Schema(description = "Коллекция комментариев")
    private List<CommentDto> results;
}