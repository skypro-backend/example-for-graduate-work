package ru.skypro.homework.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.skypro.homework.dto.AdDTO;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Accessors(chain = true)
public class Ads {
    private Integer count;
    private List<AdDTO> results;
}
