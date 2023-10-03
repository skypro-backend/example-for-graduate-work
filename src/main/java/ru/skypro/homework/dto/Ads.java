package ru.skypro.homework.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ads {

    private int count;

    private List<Ad> results;

}
