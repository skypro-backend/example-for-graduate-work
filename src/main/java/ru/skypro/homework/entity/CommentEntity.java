package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    @ManyToOne()
    @JoinColumn(name="author_id")
    private UserEntity author;
    @ManyToOne()
    @JoinColumn(name="ad_id")
    private AdEntity ad;
    private Long createdAt;
    private String text;
}
