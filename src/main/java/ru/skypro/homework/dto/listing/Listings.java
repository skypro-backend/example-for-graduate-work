package ru.skypro.homework.dto.listing;

import lombok.Data;

import java.util.List;

@Data
public class Listings {
    private Integer count;
    private List<Listing> results;
}
