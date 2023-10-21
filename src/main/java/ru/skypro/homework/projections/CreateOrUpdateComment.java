package ru.skypro.homework.projections;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class CreateOrUpdateComment {
    //    Необходимо ограничить min 8 and max 64
    private String text;
}
