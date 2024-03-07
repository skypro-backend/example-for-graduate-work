package ru.skypro.homework.dto.comments;

import lombok.Data;
import ru.skypro.homework.dto.ad.AdDto;

import java.util.List;
@Data
public class CommentsDto {
    private Integer count;
    private List<CommentDto> result;
}
