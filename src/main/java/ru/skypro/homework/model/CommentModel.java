package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "commet")
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    @Column(name = "create_data")
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "text")
    private String text;

    @Column(name ="author")
    @JoinColumn(name = "user_id")
    private int author;
}
