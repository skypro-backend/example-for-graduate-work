package ru.skypro.homework.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    Integer comments;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    Ad ad;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User author;

    @Column(name = "createdTime")
    Long createdTime;

    @Column(name = "text")
    String text;

}

