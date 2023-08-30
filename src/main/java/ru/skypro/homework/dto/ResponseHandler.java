package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseHandler<T> {


    private int count;
    private Collection<T> results;

    public static <T> ResponseHandler<T> of(Collection<T> results) {
        ResponseHandler<T> responseHandler = new ResponseHandler<>();
        if (results == null) {
            return responseHandler;
        }
        responseHandler.results = results;
        responseHandler.count = results.size();
        return responseHandler;
    }
}
