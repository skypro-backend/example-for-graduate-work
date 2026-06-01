package ru.skypro.homework.ad;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Коллекция объявлений")
public class Ads {

    @Schema(description = "Количество", example = "1000")
    private Integer count;

    @Schema(description = "Результаты", example = "-результаты-")
    private List<Ad> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Ad> getResults() {
        return results;
    }

    public void setResults(List<Ad> results) {
        this.results = results;
    }
}
