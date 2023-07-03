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
    @ManyToOne(optional = false)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ad", nullable = false)
    private Ad ad;
    @Column(name = "creating_time", nullable = false)
    private LocalDateTime time;
    @Column(name = "text")
    private String text;
}
