package ru.skypro.homework.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.skypro.homework.entity.User;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class CommentDto {
    int pk;
    User user;
    LocalDateTime createdAt;
    String text;

}
