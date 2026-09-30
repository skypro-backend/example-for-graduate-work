package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Advertisement comment")
public class Comment {

    @Schema(description = "Author ID")
    private Integer author;

    @Schema(description = "Author image URL")
    private String authorImage;

    @Schema(description = "Author first name")
    private String authorFirstName;

    @Schema(description = "Creation time in milliseconds")
    private Long createdAt;

    @Schema(description = "Comment primary key")
    private Integer pk;

    @Schema(description = "Comment text")
    private String text;
}