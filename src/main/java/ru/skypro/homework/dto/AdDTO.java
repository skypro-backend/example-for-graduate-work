package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdDTO {

    @Schema(hidden = true)
    private Long pk;
    private String title;
    private Long price;
    private String description;
    @Schema(hidden = true)
    private Long userId;


}
