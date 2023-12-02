package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ads")
public class Ad {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private int price;
    private String title;
    private String description;
    @OneToMany(mappedBy = "ad")
    private List<Comment> comments;

    public Ad(Integer id, String link, Integer pk, int price, String title) {
    }
}
