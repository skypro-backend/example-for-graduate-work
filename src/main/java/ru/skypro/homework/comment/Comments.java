package ru.skypro.homework.comment;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Все комментарии")
public class Comments {

    @Schema(description = "Количество комментариев", example = "1561")
    private Integer count;

    @Schema(description = "Результаты поиска", example = "126645")
    private List<Comment> results;

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