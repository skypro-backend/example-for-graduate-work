package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long author;

    private String authorImage;

    private String authorFirstName;

    private LocalDateTime createdAt;

    private long pk;
    @NotBlank
    @Size(min = 8)
    private String text;
}
