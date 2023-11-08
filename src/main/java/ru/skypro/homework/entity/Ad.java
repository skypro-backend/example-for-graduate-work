package ru.skypro.homework.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ads")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad {
    @Id
    @Column(name = "ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    private String title;
    private Integer price;
    private String description;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    public Ad(User author, String title, Integer price, String description) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public String getImagePath() {
        return image == null ? null : "/ads/image/" + id;
    }
}
