package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;
@Data
public class AdsDTO {
    private List<AdDTO> results;
    private Integer count;

}
