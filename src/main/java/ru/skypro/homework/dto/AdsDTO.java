package ru.skypro.homework.dto;


import lombok.Data;

import java.util.Collection;

@Data
public class AdsDTO<A> {
    private final int count;
    private Collection<AdDTO> results;
    public AdsDTO (Collection<AdDTO> results) {
        this.count = results.size();
        this.results = results;
    }
}
