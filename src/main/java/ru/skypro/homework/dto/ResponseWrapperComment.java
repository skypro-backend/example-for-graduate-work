package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ResponseWrapperComment {
    private int count;
    private Comment[] array;

}
