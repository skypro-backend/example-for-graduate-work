package ru.skypro.homework.dto.listing;

import lombok.Data;

import java.util.List;

@Data
public class ListingsDTO {
    private Integer count;
    private List<ListingDTO> results;
}
