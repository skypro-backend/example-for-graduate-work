package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.impl.ImageServiceImpl;
import ru.skypro.homework.service.impl.LoggingMethodImpl;
import ru.skypro.homework.service.impl.PhotoServiceImpl;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/photo")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class PhotoController {
    private final PhotoServiceImpl photoService;


    @GetMapping("/image/{photoId}")
    public ResponseEntity<byte[]> getPhotoFromBD(@PathVariable Integer photoId,
                                                 HttpServletResponse response){
        log.info("Запущен метод контроллера {}", LoggingMethodImpl.getMethodName());
        return ResponseEntity.ok(photoService.getPhoto(photoId, response));
    }
}
