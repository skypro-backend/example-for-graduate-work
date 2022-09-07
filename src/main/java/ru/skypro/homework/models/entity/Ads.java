package ru.skypro.homework.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private Integer price;
    private String title;
    private String description;
    private Integer image;
    private Integer author;
}
