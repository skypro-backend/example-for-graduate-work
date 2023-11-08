package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Объявления")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String image;
    private int price;
    private String title;
    private String description;
    @Transient
    private List<Comment> commentList;
}
