package ru.skypro.homework.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class ResponseWrapper<T> {

    private int count;
    private Collection<T> results;

    public static <T> ResponseWrapper<T> of(Collection<T> results) {

        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();

        if (results.isEmpty()) {

            responseWrapper.results = new ArrayList<>();
            return responseWrapper;

        }

        responseWrapper.results = results;
        responseWrapper.count = results.size();

        return responseWrapper;

    }

}
