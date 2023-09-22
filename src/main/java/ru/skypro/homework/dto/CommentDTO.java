package ru.skypro.homework.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDTO {
    int author;
    String authorImage;
    String authorFirstName;
    LocalDateTime createdAt;
    int pk;
    String text;
}
