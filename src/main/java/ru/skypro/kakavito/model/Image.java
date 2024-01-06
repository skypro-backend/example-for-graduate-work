package ru.skypro.kakavito.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The Image class represents a photo entity in the database. It is used to store binary image data associated with a user.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "images")
public class Image {

    /**
     * Id картинки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Id юзера
     *
     * @see User
     */
    @OneToOne(mappedBy = "image")
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Id объявления
     *
     * @see Ad
     */
    @OneToOne(mappedBy = "image")
    @JoinColumn(name = "ad_id")
    private Ad ad;

    /**
     * Размер фото
     */
    private Long fileSize;

    /**
     * Тип медиафайла
     */
    private String mediaType;

    /**
     * Путь к файлу
     */
    private String filePath;

    /**
     * Массив байт
     */
    @Lob
    private byte[] data;
}