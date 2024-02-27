package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * ImageEntity для объявлений
 * <br><i>содержит поля:</i>
 * <br>- id <i>(id фото)</i>;
 * <br>- filePath <i>(путь к файлу)</i>;
 * <br>- fileSize <i>(размер файла)</i>;
 * <br>- mediaType <i>(тип файла)</i>;
 * <br>- data <i>(данные файла)</i>;
 * <br>- user <i>({@link UserEntity} )</i>;
 * <br>- ad <i>({@link AdEntity} )</i>;
 */
@Entity
@Data
@Table(name = "images")
public class ImageEntity {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * путь к файлу
     */
    private String filePath;

    /**
     * размер файла
     */
    private Long fileSize;

    /**
     * тип файла
     */
    private String mediaType;
    /**
     * данные файла
     */
//    @Lob// фронт не дает "сохранять"
    private byte[] data;
    /**
     * userId
     */
    @OneToOne
    private UserEntity user;

    /**
     * AdEntity ad
     */
    @OneToOne
    private AdEntity ad;
}
