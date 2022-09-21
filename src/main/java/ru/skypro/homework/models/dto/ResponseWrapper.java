package ru.skypro.homework.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapper<T> {
    private int count;
    private List<T> results;

    public ResponseWrapper(List<T> object) {
        this.count = object.size();
        this.results = object;
    }
}