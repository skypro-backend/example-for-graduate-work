package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Класс для возвращения ID")
public class IdDto {

    @Schema(description = "ID созданной сущности")
    private Integer id;

    public IdDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}