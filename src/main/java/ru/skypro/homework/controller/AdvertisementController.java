package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;

import java.util.Collection;
/**
 * @author Michail Z. (GH: HeimTN)
 */
@RestController
@RequestMapping("/ads")
public class AdvertisementController {
    /**
     * Получение коллекции объявлений.
     */
    @Operation(summary = "Получение коллекции объявлений", description = "Метод возвращает коллекцию всех объявлений.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Коллекция объявлений успешно получена"),
    })
    @GetMapping
    public ResponseEntity<Ads> getCollectionAds(){
        //запрос в сервис...;
        return ResponseEntity.status(200).build(); //через .ok возвращаем
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
        //делаем запрос в сервис на создание нового обьявления
        //так же нужна проверка авторизации, если не авторизован возваращаем 401
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
    public ResponseEntity<ExtendedAd> getInfoAds(@PathVariable Long id){
        //Запрос в сервис на получение расширеного обьявления по id
        //если null возвращаем 404
        //если не авторизован 401
        return ResponseEntity.status(200).build();
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
    public ResponseEntity deleteAds(@PathVariable Long id){
        //Запрос в сервис на удаление с возвращением удаленного обьекта
        //если null возвращаем 404
        //если не авторизован 401
        //если нет доступа(видимо если не админ) 403
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
    public ResponseEntity<Ad> pathAds(@PathVariable Long id, CreateOrUpdateAd ads){
        //Запрос в сервис на изменение обьявление с возвращением изменяемого обьекта
        //если null возвращаем 404
        //если не авторизован 401
        //если нет доступа(видимо если не админ) 403
        return ResponseEntity.status(200).build(); //возвращаем через .ok
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
        //Запрос в сервис для получения всех обьявелний по id автора
        //если не авторизован 401
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
    public ResponseEntity pathImageAds(@PathVariable Long id, String image){
        //Запрос в сервис на изменение изображения обьявления с возвращением изменяемого обьекта
        //если null возвращаем 404
        //если не авторизован 401
        //если нет доступа(видимо если не админ) 403
        return ResponseEntity.status(200).build(); //тут должны должны открывать поток, пока не понимаю как
    }
}
