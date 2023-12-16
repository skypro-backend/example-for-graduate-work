package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Comments {
    private int count;
    private List<Comment> results;

    public Comments(int count, List<Comment> results) {
        this.count = count;
        this.results = results;
    }

}
