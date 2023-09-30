package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class Comments {
    int count;
    List<Comment> results;

}
