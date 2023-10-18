package ru.skypro.homework.service.entities;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name="ads")
public class AdsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer price;
    String title;
    String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image")
    ImageEntity image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserEntity user;
}