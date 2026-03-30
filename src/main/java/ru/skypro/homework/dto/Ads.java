package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Список объявлений")
public class Ads {

    @Schema(description = "общее количество объявлений",
            example = "10")
    private int count;

    @Schema(description = "список объявлений")
    private List<Ad> results;

    public Ads() {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public List getResults() {
        return results;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setResults(List results) {
        this.results = results;
    }
}
