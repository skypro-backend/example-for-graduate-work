package ru.skypro.homework.dto;

import java.util.List;

public class AdsDTO {
    private List<AdDTO> results;
    private Integer count;

    public List<AdDTO> getResults() {
        return results;
    }

    public void setResults(List<AdDTO> results) {
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
