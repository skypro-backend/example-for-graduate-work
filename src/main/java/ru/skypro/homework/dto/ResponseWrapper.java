package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Обертка для списков с количеством элементов")
public class ResponseWrapper<T> {

    @Schema(description = "Количество элементов в списке", example = "5")
    private int count;

    @Schema(description = "Список элементов")
    private List<T> results;
}
