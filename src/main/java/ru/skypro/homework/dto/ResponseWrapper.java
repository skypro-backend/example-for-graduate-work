package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ResponseWrapper<T> {

    private int count;
    private List<T> results;

    public ResponseWrapper(T... object) {
        this.count = object.length;
        this.results = Arrays.asList(object);
    }
}