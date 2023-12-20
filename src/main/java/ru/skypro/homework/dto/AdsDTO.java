package ru.skypro.homework.dto;


import java.util.Collection;
import java.util.Objects;


public class AdsDTO {

    private Integer count;             //общее количество объявлений

    private Collection<AdDTO> results;

    public AdsDTO(Collection<AdDTO> results) { //!!
        this.count = results.size();
        this.results = results;
    }

    public AdsDTO(Integer count) {
        this.count = count;
    }

    public AdsDTO(Integer count, Collection<AdDTO> results) {
        this.count = count;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Collection<AdDTO> getResults() {
        return results;
    }

    public void setResults(Collection<AdDTO> results) {
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
