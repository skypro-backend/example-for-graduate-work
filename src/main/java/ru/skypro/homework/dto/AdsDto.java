package ru.skypro.homework.dto;

import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
public class AdsDto {
    private int count;
    private List<AdDto> results;

    public AdsDto() {

    }

//    public AdsDto(List<AdDto> list) {
//        count = list == null ? 0 : list.size();
//        results = list;
//    }
}
