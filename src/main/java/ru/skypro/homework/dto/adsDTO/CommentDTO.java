package ru.skypro.homework.dto.adsDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    int author;
    String authorImage;
    String authorFirstName;
    long createdAt;
    int pk;
    String text;
}
