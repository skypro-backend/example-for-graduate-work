package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;

import java.io.IOException;

public interface ImageService {

    void uploadImg(AdDTO adDTO, MultipartFile img) throws IOException;

}
