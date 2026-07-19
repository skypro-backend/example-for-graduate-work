package ru.skypro.homework.dto;

import java.util.List;

public class AdsDTO {

    private Integer count;
    private List<AdDTO> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<AdDTO> getResults() {
        return results;
    }

    public void setResults(List<AdDTO> results) {
        this.results = results;
    }
}
