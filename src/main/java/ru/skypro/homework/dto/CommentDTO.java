package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class CommentDTO {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Instant createdAt;
    private Integer pk;
    private String text;

}