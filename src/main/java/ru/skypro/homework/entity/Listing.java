package ru.skypro.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.awt.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer price;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @OneToOne
    private Image image;
}
