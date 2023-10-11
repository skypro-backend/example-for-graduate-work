package ru.skypro.homework.dto.adsDTO;

import java.util.List;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CommentsDTO {
    int count;
    List<CommentDTO> results;
}
