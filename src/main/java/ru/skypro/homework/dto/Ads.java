package ru.skypro.homework.dto;


import java.util.Collection;
import java.util.List;

public class Ads {
    private int count;
    private List<Ad> results;
    public static Ads of(List<Ad> results) {
        Ads responseWrapper = new Ads();
        if (results == null) {
            return responseWrapper;
        }
        responseWrapper.results = results;
        responseWrapper.count = results.size();
        return responseWrapper;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Collection getResults() {
        return results;
    }

    public void setResults(List<Ad> results) {
        this.results = results;
    }
}
