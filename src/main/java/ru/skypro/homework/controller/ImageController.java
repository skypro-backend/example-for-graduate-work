package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.security.logger.FormLogInfo;
import ru.skypro.homework.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class ImageController {
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    private final ImageService imageService;

    @Operation(
            tags = "Пользователи",
            summary = "Обновление аватара авторизованного пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()})
            }
    )
    @PatchMapping(value = "/users/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestParam MultipartFile image,
                                             Authentication authentication) throws IOException {
        log.info("STEP_1_Обновление аватара авторизованного пользователя___" + FormLogInfo.getInfo());

        imageService.updateUserImage(image, authentication);

        return ResponseEntity.ok().build();//ПУСТЫШКА
    }





    @Operation(
            tags = "01_Avatar",
            summary = "step_2_Обновление аватара авторизованного пользователя"
    )
    @GetMapping(path = "/{imagePath}",
            produces = {
                    MediaType.IMAGE_PNG_VALUE,
                    MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_GIF_VALUE,
                    "image/*"}//
    )
    public ResponseEntity<byte[]> getImage(
            @Parameter(description = "Ссылка на изображение в файловой системе",
                    example = "user_1.jpg")
            @PathVariable String imagePath, HttpServletResponse response) {

        log.info("STEP_2_Обновление аватара авторизованного пользователя___" + FormLogInfo.getInfo());

        //return imageService.getImage(imagePath, response);
        //то есть фронтенд будет обращаться например как http://localhost:8080/image1.png
        return null;
    }

}
