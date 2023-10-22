package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class Ads {
    Integer count;
    Collection<Ad> results;
}
