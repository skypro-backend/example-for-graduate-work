package ru.skypro.homework.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Ads {
    private Integer count;
    private List<Ad> result;

    public Ads(Integer count, List<Ad> result) {
        this.count = count;
        this.result = result;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Ad> getResult() {
        return result;
    }

    public void setResult(List<Ad> result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return Objects.equals(count, ads.count) && Objects.equals(result, ads.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, result);
    }
}
