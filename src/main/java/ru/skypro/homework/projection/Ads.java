package ru.skypro.homework.projection;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.skypro.homework.dto.AdDTO;

import java.util.List;
@RequiredArgsConstructor
@Data
public class Ads {
    private final Integer count;
    private final List<AdDTO> results;
}
