package ru.skypro.homework.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"user", "comments"})
@ToString(exclude = {"user", "comments"})
@Getter
@Setter
@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private Integer pk;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Integer price;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "ad")
    private List<Comment> comments;
}
