package ru.skypro.kakavito.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The Avatar class represents a photo entity in the database. It is used to store binary image data associated with a user.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Integer id;

    /**
     * Id юзера
     */
    @OneToOne(mappedBy = "image")
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Id объявления
     */
    @OneToOne(mappedBy = "image")
    @JoinColumn(name = "ad_id")
    private Ad ad;

    /**
     * Размер фото
     */
//    @Column(name = "file_size")
    private Long fileSize;

//    @Column(name = "media_type")
    private String mediaType;

    /**
     * Путь к файлу
     */
//    @Column(name = "file_path")
    private String filePath;

    @Lob
//    @Column(name = "bytes")
    private byte[] data;
}