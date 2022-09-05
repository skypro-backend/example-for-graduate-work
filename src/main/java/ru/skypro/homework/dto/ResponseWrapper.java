package ru.skypro.homework.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseWrapper<T> {

    private int count;
    private List<T> results;

    public ResponseWrapper(T... object) {
        this.count = object.length;
        this.results = Arrays.asList(object);
    }
}
/*
public class ResponseWrapper<T> extends ResponseEntity<List<T>> {

    private int count;
    private List<T> results;

    public ResponseWrapper(T... object) {
        super(List.of(object), HttpStatus.OK);
        this.count = object.length;
    }
}

 */