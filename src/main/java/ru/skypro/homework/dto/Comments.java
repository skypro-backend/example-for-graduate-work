package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Comments {
    private Integer count; //($int32) общее кол-во комментариев
    private List<Comment> results;
}
