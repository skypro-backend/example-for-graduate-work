package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private int author = 0;
    private String authorImage = "string";
    private String authorFirstName = "string";
    private long createdAt = 0L;
    private int pk = 0;
    private String text = "string";

}
