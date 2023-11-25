package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Integer id;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private String text;
//    private Integer adId;
    private Integer userId;
    private Integer userAvatarId;




}
