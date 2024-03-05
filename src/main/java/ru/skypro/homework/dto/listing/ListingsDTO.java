package ru.skypro.homework.dto.listing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingsDTO {
    private int count;
    private List<ListingDTO> results;

    public void setCount(int count) {
        this.count = count;
    }

    public void setResults(List<ListingDTO> results) {
        this.results = results;
    }
}
