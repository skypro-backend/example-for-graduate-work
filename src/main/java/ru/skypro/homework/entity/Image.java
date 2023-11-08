package ru.skypro.homework.entity;

import lombok.*;
import javax.persistence.*;

@ToString
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Image {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private byte[] data;
}