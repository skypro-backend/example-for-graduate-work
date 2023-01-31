package ru.skypro.homework.model.entity;

import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Comment
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-16T21:17:34.091476600+03:00[Europe/Moscow]")
@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ads_id")
    private Long adsId;

    @Column(name = "user_profile_id")
    private Long author;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "text")
    private String text;

}

