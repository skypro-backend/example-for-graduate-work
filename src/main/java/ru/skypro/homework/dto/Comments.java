package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class Comments {
    Integer count;
    List<Comment> results;
}
