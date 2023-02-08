package ru.skypro.homework.model.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapper<T> {

    private final int count;
    private final Collection<T> results;

    public ResponseWrapper(Collection<T> results) {
        this.count = results.size();
        this.results = results;
    }
}
