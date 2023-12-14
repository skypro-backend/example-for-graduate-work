package ru.skypro.homework.dto.ads.comments;

import lombok.Data;

import java.util.List;

@Data
public class Comments {
    private int count;
    private List<Comment> results;
}
