package ru.skypro.homework.dto.ad;
import lombok.Data;
import ru.skypro.homework.dto.ad.AdDto;

import java.util.List;

@Data
public class AdsDto {

 private Integer count;
 private List<AdDto> result;


}
