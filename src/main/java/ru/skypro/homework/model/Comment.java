package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne
    @JoinColumn(name = "author_image_id")
    private Image authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    private String text;
}
