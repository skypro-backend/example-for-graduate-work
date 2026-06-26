package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

/**
 * REST-контроллер для выгрузки графического контента (изображений).
 * Извлекает бинарные массивы байт из базы данных и возвращает их в виде медиа-файлов.
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final UserRepository userRepository;
    private final AdRepository adRepository;

    @GetMapping(value = "/user/{id}", produces = {"image/jpeg", "image/png", "image/gif", "image/*"})
    public ResponseEntity<byte[]> getUserImage(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        if (user.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getImageData());
    }

    @GetMapping(value = "/ad/{id}", produces = {"image/jpeg", "image/png", "image/gif", "image/*"})
    public ResponseEntity<byte[]> getAdImage(@PathVariable Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow();
        if (ad.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ad.getImageData());
    }
}