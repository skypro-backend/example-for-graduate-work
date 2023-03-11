package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private Date createdAt;
    private Integer author;

    @ManyToOne
    @JoinColumn (name = "ads_id")
    private AdsEntity ads;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
