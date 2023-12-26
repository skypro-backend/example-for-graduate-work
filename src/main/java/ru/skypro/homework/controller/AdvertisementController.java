package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.repo.UserRepo;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.impl.AuthServiceImpl;


import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Michail Z. (GH: HeimTN)
 */
@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class AdvertisementController {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final AdService adService;



    /**
     * Получение коллекции объявлений.
     */
    @Operation(summary = "Получение коллекции объявлений", description = "Метод возвращает коллекцию всех объявлений.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Коллекция объявлений успешно получена"),
    })
    @GetMapping
    public ResponseEntity<Ads> getCollectionAds(){
        Ads result = adService.getAllAds();
        return ResponseEntity.ok(result);
    }
    /**
     * Создание нового объявления.
     */
    @Operation(summary = "Создание нового объявления", description = "Метод создает новое объявление с заданными свойствами и изображением.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Объявление успешно создано"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> postAds(@RequestPart("properties") @Valid CreateOrUpdateAd properties,
                                      @RequestPart("image") MultipartFile image){
        logger.info("Request to create Ad, title: {}, price: {}, descr: {}, image: {}",properties.getTitle(), properties.getPrice()
        , properties.getDescription(), image);
        Ad result = adService.createAd(properties, image);
        return ResponseEntity.status(201).body(result);
    }
    /**
     * Получение информации об объявлении по его идентификатору.
     */
    @Operation(summary = "Получение информации об объявлении", description = "Метод возвращает информацию об объявлении по его идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация об объявлении успешно получена"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "404", description = "Объявление с указанным идентификатором не найдено")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getInfoAds(@PathVariable Integer id){
        ExtendedAd result = adService.getExtAd(id);
        if(result == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(result);
    }
    /**
     * Удаление объявления по его идентификатору.
     */
    @Operation(summary = "Удаление объявления", description = "Метод удаляет объявление по его идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Объявление успешно удалено"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "403", description = "Отсутствует доступ"),
            @ApiResponse(responseCode = "404", description = "Объявление с указанным идентификатором не найдено")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAds(@PathVariable Integer id){
        Ad result = adService.deleteAd(id);
        if(result == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(204).build();
    }
    /**
     * Изменение объявления по его идентификатору.
     */
    @Operation(summary = "Изменение объявления", description = "Метод изменяет объявление по его идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Объявление успешно изменено"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "403", description = "Отсутствует доступ"),
            @ApiResponse(responseCode = "404", description = "Объявление с указанным идентификатором не найдено")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> pathAds(@PathVariable Integer id,@RequestBody @Valid CreateOrUpdateAd ads){
        Ad result = adService.pathAd(ads, id);
        if(result == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(result);
    }
    /**
     * Получение списка всех объявлений пользователя.
     */
    @Operation(summary = "Получение списка объявлений пользователя", description = "Метод возвращает все объявления, созданные текущим пользователем.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список объявлений пользователя успешно получен"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ")
    })
    @GetMapping("/me")
    public ResponseEntity<Ads> getListAdsUser(){
        Ads result = adService.getAllAdsForUser();
        return ResponseEntity.ok(result);
    }
    /**
     * Изменение изображения объявления по его идентификатору.
     */
    @Operation(summary = "Изменение изображения объявления", description = "Метод изменяет изображение объявления по его идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение объявления успешно изменено"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "403", description = "Отсутствует доступ"),
            @ApiResponse(responseCode = "404", description = "Объявление с указанным идентификатором не найдено")
    })
    @PatchMapping("/{id}/image")
    public ResponseEntity<byte[]> pathImageAds(@PathVariable Integer id, MultipartFile image) throws IOException {
        byte[] imageBytes = adService.pathImageAd(id, image).getBytes();
        if(imageBytes == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(imageBytes);
    }
}
