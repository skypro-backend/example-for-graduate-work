package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.Ad;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userFirstName;
    private LocalDateTime createdAt;
    private String text;

    public Comment(String userFirstName, LocalDateTime createdAt, String text,  User user, Image image) {

        this.userFirstName = userFirstName;
        this.createdAt = createdAt;
        this.text = text;
//        this.ad = ad;
        this.user = user;
        this.image = image;
    }

//    @ManyToOne
//    @JoinColumn(name = "ad_id")
//    private Ad ad;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "user_avatar_id")
    private Image image;

    public Comment() {

    }
}
