package ru.skypro.homework.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Comment
 */

@Entity
@Data
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ads_id")
    private Long adsId;

    @Column(name = "author_id")
    private Long author;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "text")
    private String text;
}
