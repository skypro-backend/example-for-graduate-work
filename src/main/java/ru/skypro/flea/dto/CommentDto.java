package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CommentDto {

    @Schema(description = "Comment's author id")
    private Integer author;

    @Schema(description = "Comment's author avatar link")
    private String authorImage;

    @Schema(description = "Comment's author first name")
    private String authorFirstName;

    @Schema(description = "Comment's creation timestamp")
    private Long createdAt;

    @Schema(description = "Comment's id")
    private Integer pk;

    @Schema(description = "Comment's text")
    private String text;

}
