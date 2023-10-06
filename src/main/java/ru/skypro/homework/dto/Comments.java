package ru.skypro.homework.dto;

import lombok.*;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    private int count;
    private List<Comment> results;

}
