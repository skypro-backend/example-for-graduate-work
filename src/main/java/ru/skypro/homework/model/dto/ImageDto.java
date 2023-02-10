package ru.skypro.homework.model.dto;

import lombok.Data;
import ru.skypro.homework.model.entity.Ads;

import javax.persistence.*;
@Data
public class ImageDto {

    private Integer id;
    private String generatedIdFromMultipartFile;
    private long fileSize;
    private String mediaType;
    private byte[] data;
    private Ads ads;
}
