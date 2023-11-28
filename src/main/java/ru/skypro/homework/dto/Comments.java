package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class Comments {

    @Schema(description = "общее количество комментариев")
    @NotBlank
    private Integer count;


    @Schema(description = "#/components/schemas/Comment")
    @NotBlank
    private List<Comment> results;


}

