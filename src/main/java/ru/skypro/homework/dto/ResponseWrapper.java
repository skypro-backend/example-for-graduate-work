package ru.skypro.homework.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseWrapper<T> extends ResponseEntity<T> {

    private int count;
    private List<T> results;

    public ResponseWrapper(T object) {
        super(object, HttpStatus.OK);
    }
}
