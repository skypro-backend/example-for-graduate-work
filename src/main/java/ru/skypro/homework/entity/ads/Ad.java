package ru.skypro.homework.entity.ads;

import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.entity.comments.Comment;
import ru.skypro.homework.entity.users.User;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer pk;

    @Column(name = "image")
    private String image;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<Comment> comments;

}