package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class Comments {
    private List<Comment> Listcomment;
    private   int count;
}
