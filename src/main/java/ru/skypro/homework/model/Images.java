package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

/**
 * <h2>Images</h2>
 * Entity keeping images: user avatars and photos of items in advertisements<br>
 * pictureType field of {@link PictureType} class is used to recognize avatars from item photos
 */
@Data
@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "ad_id")
    private long adId;

    @Enumerated(EnumType.STRING)
    @Column(name = "picture_type")
    private PictureType pictureType;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private long fileSize;

    @Column(name = "media_type")
    private String mediaType;

    @Lob
    @Column(name = "data")
    private byte[] data;
}
