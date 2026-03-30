package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Список комментариев")
public class Comments {

    @Schema(description = "общее количество комментариев", example = "5")
    private Integer count;

    @Schema(description = "список комментариев")
    private List<Comment> results;

    public Comments() {
        this.results = new ArrayList<>();
    }

    // Геттеры и сеттеры
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Comment> getResults() {
        return results;
    }

    public void setResults(List<Comment> results) {
        this.results = results;
    }
}