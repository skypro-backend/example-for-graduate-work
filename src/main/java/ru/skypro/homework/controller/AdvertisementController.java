package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

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
public class AdvertisementController {
    private AdService adService;
    public AdvertisementController(AdService adService){
        this.adService = adService;
    }


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
    @PostMapping
    public ResponseEntity<Ad> postAds(CreateOrUpdateAd properties, String image){
        //так же нужна проверка авторизации, если не авторизован возваращаем 401
       // Ad result = adService.createAd(properties, image, userId);
        return ResponseEntity.status(201).build();
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
        //если не авторизован 401
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
        //если не авторизован 401
        //если нет доступа(видимо если не админ) 403
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
    public ResponseEntity<Ad> pathAds(@PathVariable Integer id, CreateOrUpdateAd ads){
        //если не авторизован 401
        //если нет доступа(видимо если не админ) 403
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
        //если не авторизован 401
       // Ads result = adService.getAllAdsForUser(userId);
        return ResponseEntity.status(200).build(); //Возвращаем через .ok
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
    public ResponseEntity<byte[]> pathImageAds(@PathVariable Integer id, String image) throws IOException {
        //если не авторизован 401
        //если нет доступа(видимо если не админ) 403
        String result = adService.pathImageAd(id, image);
        if(result == null){
            ResponseEntity.status(404).build();
        }

        File imageFile = new File(image);
        InputStream imageStream = new FileInputStream(imageFile);
        byte[] imageBytes = new byte[(int) imageFile.length()];
        imageStream.read(imageBytes);

        Path path = Paths.get(image);
        String mimeType = Files.probeContentType(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mimeType));
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
