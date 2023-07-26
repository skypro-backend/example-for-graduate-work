package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateCommentDto {

    @Schema(description = "Comment's text")
    @Size(min = 8, max = 64)
    @NotNull
    private String text;

}
