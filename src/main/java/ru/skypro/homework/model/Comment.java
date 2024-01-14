package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_ad")
    private Ad ad;

    }
