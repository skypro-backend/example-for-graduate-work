package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.service.ImageService;

/**
 * Контроллер для получения изображений.
 * Обрабатывает запросы на скачивание картинок из базы данных.
 * Основные функции:
 * - получение изображения по его ID в формате byte[].
 */
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    /**
     * Получить изображение по ID.
     * Доступно без авторизации.
     * @param id ID изображения
     * @return байтовый массив изображения с правильным Content-Type
     */
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        ImageEntity image = imageService.getImage(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getContentType()))
                .body(image.getData());
    }
}
