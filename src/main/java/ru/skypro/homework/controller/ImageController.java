package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.util.Map;

@RestController
@RequestMapping("/image")
@Tag(name = "Изображение")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {

    private final ImageService imageService;

    public ImageController (ImageService imageService) {
        this.imageService = imageService;
    }

    @Operation(summary = "Загрузить картинку в объявление")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    mediaType = "application/octet-stream",
                                    array = @ArraySchema(schema = @Schema(implementation = ImageDTO.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found"
            )
    })
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "{id}")
    public ResponseEntity<?> uploadImage(@PathVariable (name = "id") Integer id, @RequestBody ImageDTO imageDTO) {
        imageService.uploadImage(id);
        return ResponseEntity.ok().build();
    }

}
