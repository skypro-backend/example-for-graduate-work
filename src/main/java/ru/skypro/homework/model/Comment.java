package ru.skypro.homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    private Integer time;
    @ManyToOne
    @JoinColumn(name = "ads_id")
    private Ad ad;
    private String text;

    public Comment(User user, Integer time, String text) {
            this.user = user;
            this.time = time;
            this.text = text;
        }
    }
