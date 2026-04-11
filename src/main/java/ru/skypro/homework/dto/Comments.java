package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

/**
 * DTO-класс, представляющий контейнер для списка комментариев.
 * Включает общее количество комментариев и список самих комментариев.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Контейнер для списка комментариев")
public class Comments {

    @Schema(description = "общее количество комментариев", example = "3")
    private Integer count;

    @Schema(description = "список комментариев")
    private List<Comment> results;

}
