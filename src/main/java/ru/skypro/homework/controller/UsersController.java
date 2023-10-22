package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.AvatarService;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name="Пользователи")
public class UsersController {
    private static Logger logger = LoggerFactory.getLogger(UsersController.class);

    private final AvatarService avatarService;

    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)}),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@RequestParam MultipartFile image) {
        logger.info("Avatar Controller {}", image.getContentType());
        String username = "authenticatedUsername"; // Get from Authentication
        String imageString = avatarService.uploadAvatar(username, image);
        if (imageString == null) {
            logger.info("Unable to save avatar: {}", image.getName());
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        logger.info("Avatar saved");
        return ResponseEntity.ok().build();
    }
}
