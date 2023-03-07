package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.Table;
import ru.skypro.homework.dto.Comment;

import javax.persistence.*;
import java.util.List;

@Entity
// @Table(name = "Ads")
@Data
public class AdsEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private String image;
        private int price;
        private String description;

        // @ManyToOne
        // @JoinColumn(name = "user_id")
        private UserEntity user; // когда присоединим user
       // @OneToMany(mappedBy = "commentEntity")
       // private List<Comment> results; // присоединение comment

}
