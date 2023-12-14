package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.core.io.Resource;

/**
 * ImageBody -- DTO for photos of items
 */

@Data
public class ImageBody {
    private Resource image;
}
