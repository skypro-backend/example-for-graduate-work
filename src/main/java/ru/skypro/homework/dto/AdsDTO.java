package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class AdsDTO {
    private int count;
    private List<AdDTO> adDTOList;

}
