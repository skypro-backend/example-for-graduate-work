package ru.skypro.homework.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
@Accessors(chain = true)

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "author")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ad")
    private Ad ad;
}
