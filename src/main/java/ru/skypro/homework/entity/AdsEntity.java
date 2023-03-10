package ru.skypro.homework.entity;

import lombok.Data;
import javax.persistence.*;
@Entity
@Data
@Table(name = "ads")

public class AdsEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String title;
        private Integer imageId;
        private int price;
        private String description;

      //  private Integer authorId;
       @ManyToOne
       @JoinColumn(name = "author_id")
       private UserEntity author;
       // @OneToMany(mappedBy = "commentEntity")
       // private List<CommentEntity> results; // присоединение comment
}
