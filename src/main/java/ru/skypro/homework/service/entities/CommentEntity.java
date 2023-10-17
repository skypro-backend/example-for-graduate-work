package ru.skypro.homework.service.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Entity(name = "comments")
@FieldDefaults(level = AccessLevel.PRIVATE)
    public class CommentEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;
        LocalDateTime createdAt;
        String text;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        UserEntity user;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ads_id")
        AdsEntity ads;
}