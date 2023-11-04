package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
//@NoArgsConstructor

public class CommentsDto {
    private int count;
    private List<CommentDto> results;

//    public CommentsDto(List<CommentDto> list) {
//        count = list == null ? 0 : list.size();
//        results = list;
//    }
}
