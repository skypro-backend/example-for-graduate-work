package ru.skypro.homework.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private byte[] data;
    private String filePath;
    private Integer fileSize;
    private String mediaType;
}
