package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

import static liquibase.repackaged.net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Entity
@Data
@Table(name = "Комментарии")
public class Comment {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private long createdAt;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    private String text;
}
