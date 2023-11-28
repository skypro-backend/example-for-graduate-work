package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    private Image authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private String text;
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;

}
