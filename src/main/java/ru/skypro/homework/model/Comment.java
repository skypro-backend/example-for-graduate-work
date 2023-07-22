package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ad_id",  referencedColumnName = "id")
    private Ad ad;

    @Column(
            name = "creating_time",
            nullable = false
    ) private LocalDateTime time;
    @Column(name = "text") private String text;

    public Comment(User user, Ad ad, LocalDateTime time, String text) {
        this.user = user;
        this.ad = ad;
        this.time = time;
        this.text = text;
    }
}
