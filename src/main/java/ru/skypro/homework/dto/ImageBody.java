package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * ImageBody -- DTO for photos of items
 */

@Data
public class ImageBody {
    private MultipartFile image;
}
