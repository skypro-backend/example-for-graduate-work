package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ads {

    private Integer count;
    private List<Ad> results;

    public Ads(List<Ad> results) {
        this.count = results.size();
        this.results = results;
    }
}
