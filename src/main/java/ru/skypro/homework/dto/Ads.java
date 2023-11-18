package ru.skypro.homework.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ads {
    private int count;
    private List<Ad> results;
}