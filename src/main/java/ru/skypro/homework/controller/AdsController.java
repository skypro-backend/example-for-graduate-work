package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;

import javax.validation.Valid;
import java.util.ArrayList;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления",
            description = "Удаление объявления по идентификационному номеру авторизованным пользователем")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<Void> removeAd(@PathVariable("id") Integer id) {
        // Реализация удаления объявления
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Получить все объявления", description = "getAllAds", tags = {"Объявления"})
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<AdsDto> getAllAds() {
        AdsDto AdsDtoList = new AdsDto(0, new ArrayList<>());
        return ResponseEntity.ok(AdsDtoList);
    }

    @SneakyThrows
    @Operation(summary = "Добавить объявление", description = "addAds", tags = {"Объявления"})
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAds(@Parameter(description = "Данные нового объявления")
                                         @RequestPart("image") MultipartFile imageFile,
                                         @Valid @RequestPart("properties") AdsDto createAdsDto, Authentication authentication) {
        return ResponseEntity.ok(createAdsDto);
    }

    @Operation(summary = "Получить информацию об объявлении", description = "getFullAd", tags = {"Объявления"})
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @GetMapping("/{adId}")
    public ResponseEntity<AdsDto> getAuthById(@PathVariable("adId") Integer adId,
                                              @RequestBody AdsDto adsDto) {
        return ResponseEntity.ok(adsDto);
    }
}



