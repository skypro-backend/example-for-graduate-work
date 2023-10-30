package ru.skypro.homework.entity;

import lombok.*;
import javax.persistence.*;
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor
@Entity
public class Image {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private byte[] data;
}