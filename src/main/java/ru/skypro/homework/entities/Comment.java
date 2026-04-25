package ru.skypro.homework.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "Comments")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long pk;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @ManyToOne
    @JoinColumn(name = "ad")
    private Ad ad;

    @Column(name = "author_image")
    private String authorImage;

    @Column(name = "author_name")
    private String authorFirstName;

    @Column(name = "created_time")
    private LocalDateTime createdAt;

    @Column(name = "text")
    private String text;
}
