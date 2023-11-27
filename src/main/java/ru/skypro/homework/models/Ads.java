package ru.skypro.homework.models;

import lombok.Data;

import java.util.List;
@Data
public class Ads {
    private Integer count;
    private List<Ads> results;
}
