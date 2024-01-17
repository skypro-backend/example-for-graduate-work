package ru.skypro.homework.dto.listing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingsDTO {
    private int count;
    private List<ListingDTO> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListingDTO> getResults() {
        return results;
    }

    public void setResults(List<ListingDTO> results) {
        this.results = results;
    }
}
