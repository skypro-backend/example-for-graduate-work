package ru.skypro.homework.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ads {
    private int count;
    private List<Ad> results;
}