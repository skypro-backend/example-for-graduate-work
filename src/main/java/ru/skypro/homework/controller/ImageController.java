package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.FileStorageService;

import javax.servlet.http.HttpServletRequest;

/**
 * Контроллер для загрузки и получения изображений.
 * Отдает файлы изображений по пути из файловой системы.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {
    private final FileStorageService fileStorageService;

    /**
     * Получение изображения по URL пути.
     * @param request HTTP-запрос с путем изображения
     * @return изображение в виде массива байт
     */
    @GetMapping(value = "/**", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> getImage(HttpServletRequest request) {
        String path = request.getRequestURI().replace("/images", "");
        byte[] image = fileStorageService.getImage(path);
        return ResponseEntity.ok(image);
    }
}
