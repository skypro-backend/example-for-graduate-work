package ru.skypro.homework.dto;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class AdsDTO {

    private Integer count;             //общее количество объявлений

    private List<AdDTO> results;

    public AdsDTO count(Integer count) {
        this.count = count;
        return this;
    }
    public AdsDTO results(List<AdDTO> results) {
        this.results = results;
        return this;
    }
    public AdsDTO addResultsItem(AdDTO resultsItem) {
        if (this.results == null) {
            this.results = new ArrayList<>();
        }
        this.results.add(resultsItem);
        return this;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdsDTO adsDTO = (AdsDTO) o;
        return Objects.equals(count, adsDTO.count) && Objects.equals(results, adsDTO.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }

    @Override
    public String toString() {
        return "AdsDTO{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}