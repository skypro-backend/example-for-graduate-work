package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class Ads {

    private Integer count;
    private List<Ad> results;
}
