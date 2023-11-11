package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private long authorId;
    private String authorImage;
    private String authorFirstName;
    private String createdAt;
    private long pk;
    private String text;

}
