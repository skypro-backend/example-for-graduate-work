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
    private Integer id;

    @Column(name = "ads_id")
    private Integer adsId;

    @Column(name = "author_id")
    private Integer author;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "text")
    private String text;
}
