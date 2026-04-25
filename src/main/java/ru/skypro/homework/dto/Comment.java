package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private long author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private long pk;
    private String text;
}
