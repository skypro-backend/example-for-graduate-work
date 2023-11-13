package ru.skypro.homework.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "comments_text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;

    public Comment (User author, LocalDateTime createdAt, String text, Ad ad) {
        this.author = author;
        this.createdAt = createdAt;
        this.text = text;
        this.ad = ad;
    }
}
