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
@AllArgsConstructor
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user", nullable = false)
    @ManyToOne(optional = false)
    private User user;

    @Column(name = "ad", nullable = false)
    @ManyToOne
    private Ad ad;
    @Column(name = "creating_time", nullable = false)
    private LocalDateTime time;
    @Column(name = "text")
    private String text;
}
