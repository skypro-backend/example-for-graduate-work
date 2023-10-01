package ru.skypro.homework.dto.adsDTO;

import java.util.List;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AdsAllCommentsDTO {
    int count;
    List<AdsCommentDTO> results;
}
