package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private Integer pk;
    private String text;

}
