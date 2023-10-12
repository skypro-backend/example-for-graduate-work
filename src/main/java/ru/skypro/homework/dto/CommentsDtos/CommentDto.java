package ru.skypro.homework.dto.CommentsDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private int Author;
    private String AuthorImage;
    private String AuthorFirstName;
    private int CreatedAt;
    private int Pk;
    private String Text;
}
