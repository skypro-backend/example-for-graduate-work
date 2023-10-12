package ru.skypro.homework.dto.adsDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CommentDTO {
    int author;
    String authorImage;
    String authorFirstName;
    long createdAt;
    int pk;
    String text;
}
