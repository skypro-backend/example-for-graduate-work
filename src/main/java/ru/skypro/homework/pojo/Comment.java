package ru.skypro.homework.pojo;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "text")
    private String text;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "pk", referencedColumnName = "pk", insertable = false, updatable = false)
    private Ad ad;

    @Column(name = "pk")
    private Long pk;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @CreationTimestamp
    @Column(name = "comment_timestamp")
    private Instant timeStamp;

}


