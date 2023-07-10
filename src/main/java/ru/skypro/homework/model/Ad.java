package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ads")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ad {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id",  referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "creating_time", nullable = false)
    private LocalDateTime time;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "text")
    private String text;
    @Column(name = "price")
    private int price;
}
