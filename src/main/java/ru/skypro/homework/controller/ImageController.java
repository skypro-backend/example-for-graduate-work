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
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.loger.FormLogInfo;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;

@RestController
@RequestMapping("/ads")
@Tag(name = "Изображение")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {

    private AdsService adsService;

    public ImageController(AdsService adsService) {
        this.adsService = adsService;
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
    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@PathVariable (name = "id") Integer id, @RequestParam(name = "image") MultipartFile image) throws IOException {
        adsService.uploadImage(id, image);
        return ResponseEntity.ok().build();
    }

  @Operation(summary = "Получить аватарку объявления")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK"
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized"
      ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden"
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found"
      )
  })
  @GetMapping(value = "/ads_photo_dir/{id}", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<byte[]> getAdsImage(@PathVariable(value = "id") Integer id) {
    log.info(FormLogInfo.getInfo());
    return ResponseEntity.ok(adsService.getPhotoById(id));
  }

}
