package ru.skypro.homework.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "avatar")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avatar_id")
    private Long avatarId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "data")
    private byte[] data;

}
