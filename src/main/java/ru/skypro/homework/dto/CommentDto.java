package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
// commenty
public class CommentDto {
    private Integer authorId;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Integer pkId;
    private String text;


}
