package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image authorImage;

    private String authorName;
    private Long dataAndTimeCreate;
    private String text;

}
