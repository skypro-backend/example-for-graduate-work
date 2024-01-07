package ru.skypro.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "listings_id")
    private Listing listing;
    private String text;

    public Comment(User user, LocalDateTime time, String text) {
        this.user = user;
        this.time = time;
        this.text = text;
    }

}
