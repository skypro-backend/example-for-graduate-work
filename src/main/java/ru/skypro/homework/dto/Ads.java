package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class Ads {

    private int count; // общее количество объявлений
    private List<Ad> results;
}
