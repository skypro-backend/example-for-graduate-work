package ru.skypro.homework.dto.adsDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AdsDTO {
    Integer count;
    List<AdDTO> results;
}