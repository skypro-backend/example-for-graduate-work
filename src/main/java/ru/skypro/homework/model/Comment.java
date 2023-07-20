package ru.skypro.homework.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    Long comments;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    Ad ad;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User author;

    Long createdTime;

    String text;

}

