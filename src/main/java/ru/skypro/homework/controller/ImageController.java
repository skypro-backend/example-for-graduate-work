package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.security.logger.FormLogInfo;
import ru.skypro.homework.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @Operation(
            tags = "Пользователи",
            summary = "Обновление аватара авторизованного пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()})
            }
    )
    @PatchMapping(value = "/users/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestParam MultipartFile image,
                                                Authentication authentication) throws IOException {
        log.info("STEP_1_Обновление аватара авторизованного пользователя___" + FormLogInfo.getInfo());

        imageService.updateUserImage(image, authentication);

        return ResponseEntity.ok().build();
    }
//**************************************************

    @Operation(
            tags = "Avatar",
            summary = "step_2_Обновление аватара авторизованного пользователя"
    )

    @GetMapping(value = "{imagePath}",
            produces = {
                    MediaType.IMAGE_PNG_VALUE,
                    MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_GIF_VALUE
                    , "image/*"
            }//
    )
//    public byte[] getImage(
    public ResponseEntity<byte[]> getImage(
            @Parameter(description = "Ссылка на изображение в файловой системе", example = "avatars_1.jpg")
            @PathVariable String imagePath, HttpServletResponse response) throws IOException {

        log.info("STEP_2_Обновление аватара авторизованного пользователя___" + FormLogInfo.getInfo());

        ImageEntity avatar = imageService.findUserAvatar(imagePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

//        return imageService.getImage(imagePath);
//        System.out.println("avatar.getData() = " + Arrays.toString(avatar.getData()));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }
}
