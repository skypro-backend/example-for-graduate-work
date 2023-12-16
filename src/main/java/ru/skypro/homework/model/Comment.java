package ru.skypro.homework.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    int id;
    long createdAt;
    String text;
    @ManyToOne
    User author;
    @ManyToOne
    Ad ad;
}
