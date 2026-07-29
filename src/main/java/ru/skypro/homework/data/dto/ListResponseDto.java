package ru.skypro.homework.data.dto;

import java.util.List;

public class ListResponseDto<T> {
    private Integer count;
    private List<T> results;
}
