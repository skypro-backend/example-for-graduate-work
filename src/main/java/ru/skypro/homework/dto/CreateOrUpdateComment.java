package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateComment {
//    Необходимо ограничить min 8 and max 64
private String text;
}
