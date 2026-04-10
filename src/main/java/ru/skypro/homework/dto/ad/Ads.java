package ru.skypro.homework.dto.ad;

import java.util.List;

public class Ads {

    private Integer count;
    private List<Ad> results;

    public Ads() {
    }

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
