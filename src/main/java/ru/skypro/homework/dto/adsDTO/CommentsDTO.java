package ru.skypro.homework.dto.adsDTO;

import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CommentsDTO {
    int count;
    List<CommentDTO> results;
}
