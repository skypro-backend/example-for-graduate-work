package ru.skypro.homework.data.dto.ads;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class CreateOrUpdateAd {
    private String title;
    private String description;
    private BigDecimal price;
    private MultipartFile image;
}
