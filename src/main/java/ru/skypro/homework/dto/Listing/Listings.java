package ru.skypro.homework.dto.Listing;

import lombok.Data;

import java.util.List;

@Data
public class Listings {
    private Integer count;
    private List<Listing> results;
}
