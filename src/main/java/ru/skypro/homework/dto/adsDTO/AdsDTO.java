package ru.skypro.homework.dto.adsDTO;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdsDTO {
    Integer count;
    List<AdDTO> results;
}