package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class AdsComment {
    @Id
    @GeneratedValue
    private int id;
    private int author;
    private int pk;
    private LocalDateTime createdAt;
    private String text;
}
