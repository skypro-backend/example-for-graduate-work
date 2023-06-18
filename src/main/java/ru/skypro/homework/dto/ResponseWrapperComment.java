package ru.skypro.homework.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseWrapperComment {
    private Integer count;
    private ArrayList<Comment> results;
}
