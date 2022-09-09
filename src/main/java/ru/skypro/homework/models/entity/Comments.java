package ru.skypro.homework.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @Column(name = "author_id")
    private Integer author;
    private Integer ads_pk;
    private String createdAt;
    private String text;
}
