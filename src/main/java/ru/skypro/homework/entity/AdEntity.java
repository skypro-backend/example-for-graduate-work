package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AdEntity {

    private int price;
    private String title;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity author;

//    @OneToOne(orphanRemoval = true)
//    @JoinColumn(name = "image_id")
    private String image;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<CommentEntity> adComments;

    public String getDescription() {
        return description;
    }

}