package ru.skypro.homework.projection;

import lombok.*;
import lombok.experimental.Accessors;
import ru.skypro.homework.dto.AdDTO;

import java.util.List;
@NoArgsConstructor
@Getter @Setter
@Accessors(chain = true)
public class Ads {
    private Long count;
    private List<AdDTO> results;
}
