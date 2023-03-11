package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
//@Table(name = "comments")
    public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private LocalDateTime createdAt;
  /*  @ManyToOne
    @JoinColumn(name = "ads_id")
    private AdsEntity ads;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user; */
}
