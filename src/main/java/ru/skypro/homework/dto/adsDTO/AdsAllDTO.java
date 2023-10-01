package ru.skypro.homework.dto.adsDTO;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdsAllDTO {
    Integer count;
    List<AdDTO> results;
}