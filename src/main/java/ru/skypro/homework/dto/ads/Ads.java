package ru.skypro.homework.dto.ads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Ads {

    private int count;
    private List<AdDTO> results;

    public Ads(List<AdDTO> results) {
        this.results = results;
        this.count = results.size();
    }
}