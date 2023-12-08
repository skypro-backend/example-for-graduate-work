package ru.skypro.homework.model;


import jakarta.persistence.*;
import lombok.Data;
import ru.skypro.homework.dto.CommentDTO;

import java.time.LocalDateTime;
/**
 * Класс описывающий сущность Комментарий
 */
@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;

    private long createdAt = System.currentTimeMillis();
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    UserInfo author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ads_id")
    Ads ads;

}