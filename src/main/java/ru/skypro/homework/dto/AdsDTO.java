package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
public class AdsDTO {

    private Integer count;             //общее количество объявлений
    private Collection<AdDTO> results;

    public AdsDTO(Collection<AdDTO> results) {
        this.count = results.size();
        this.results = results;
    }
}
