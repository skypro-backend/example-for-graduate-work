package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class Ads {
    private Long count;
    private List<AdDTO> results;
}
