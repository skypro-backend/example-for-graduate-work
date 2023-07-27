package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Ad;

import java.util.List;

@Data
public class Ads {
    Integer count;
    List<Ad> results;
}
