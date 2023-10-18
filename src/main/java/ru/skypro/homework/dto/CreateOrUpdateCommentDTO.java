package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class CreateOrUpdateCommentDTO {
    //    Необходимо ограничить min 8 and max 64
    private String text;
}
