package ru.skypro.homework.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", nullable = false)
    private AdEntity ad;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Long createdAt;

    public CommentEntity(String text, UserEntity author, AdEntity ad) {
        this.text = text;
        this.author = author;
        this.ad = ad;
    }
}