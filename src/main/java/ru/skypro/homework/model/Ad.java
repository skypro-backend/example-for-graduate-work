package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ads")
@Data
@AllArgsConstructor
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(targetEntity = Image.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id")
    private Image image;
    private String description;
    private Integer price;
    private String title;
    @OneToMany(mappedBy = "ad", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Comment> comments =new ArrayList<>();

    public Ad(User user, Image image, Integer id, String description, Integer price, String title) {
        this.user = user;
        this.image = image;
        this.id = id;
        this.description = description;
        this.price = price;
        this.title = title;
    }

    public Ad(User user, String description, Image image, Integer price, String title) {
        this.user = user;
        this.image = image;
        this.description = description;
        this.price = price;
        this.title = title;
    }

    public Ad() {
    }
    public Image getImage() {
        return image;
    }
}
