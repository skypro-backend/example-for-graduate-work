package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;
/**
 * @author Michail Z. (GH: HeimTN)
 */
@Data
public class Ads {
    private int count;
    private Collection<Ad> results;
}
