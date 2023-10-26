package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ads")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;
    private long author;
    private String image;
    private int price;
    private String title;
    @Column(columnDefinition = "text")
    private String description;
}
