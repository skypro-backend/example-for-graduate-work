package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Комментарии")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private String text;
}
