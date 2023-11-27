package ru.skypro.homework.dto;

import java.util.List;
import java.util.Objects;

public class AdsDto {
    private Integer count;
    private List<AdDto> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<AdDto> getResults() {
        return results;
    }

    public void setResults(List<AdDto> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdsDto adsDto = (AdsDto) o;
        return Objects.equals(count, adsDto.count) && Objects.equals(results, adsDto.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }
}
