package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userFirstName;
    private LocalDateTime createdAt;
    private String text;
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "user_avatar_id")
    private Image image;

}
