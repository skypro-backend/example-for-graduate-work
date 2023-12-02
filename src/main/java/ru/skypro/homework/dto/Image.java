package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    private int id = 0;
    private String filePath = "string";
    private long fileSize = 0L;
    private String mediaType = "string";
    private byte[] data = {0, 0, 0};
    private String link = "string";

}
