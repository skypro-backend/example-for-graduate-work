package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

/**
 * The Avatar class represents a photo entity in the database. It is used to store binary image data associated with a user.
 */
@Entity
@Data
@Table(name = "avatar")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avatar_id")
    private Long avatar_id;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "file_path")
    private String filePath;

    @Lob
    @Column(name = "data")
    private byte[] data;
}
