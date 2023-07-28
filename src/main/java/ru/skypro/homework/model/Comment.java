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
    Integer commentId;

    @Column(name = "ad_id")
       Integer ad;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User author;

    Long createdTime;

    String text;


}


