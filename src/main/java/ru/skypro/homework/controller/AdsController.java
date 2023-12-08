package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;

import java.util.List;

/**
 * Класс-контроллер для обработки запросов об обявлениях и комментариях
 * @see AdsService
 * @see CommentService
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final CommentService commentService;

    /**
     * Метод возвращает список объявлений
     * @return {@link AllAdsDTO}
     */
    @GetMapping
    @Operation(summary = "Получить все объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    public AllAdsDTO getAllAds() {

        return adsService.getAllAds();
    }

    /**
     * Метод для добавления объявления
     * @param image
     * @param properties
     * @return {@link AdsDTO}
     */
    @PostMapping
    @Operation(summary = "Добавление объявления")
    @ApiResponse(responseCode = "200", description = "Created")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public AdsDTO addAds(@RequestPart(name = "image") MultipartFile image,
                         @RequestPart(name = "properties") CreateAdsDTO properties) {

        return adsService.addAds(image, properties);
    }

    /**
     * Метод для получения информации об объявлении
     * @param id
     * @return {@link AdsInfoDTO}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получить информацию об объявлении по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public AdsInfoDTO getAdsById(@PathVariable Integer id) {

        return adsService.getAdsById(id);
    }

    /**
     * Метод для удаления объявления
     * @param id
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить объявление по id")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public void deleteAds (@PathVariable Integer id) {
        adsService.deleteAds(id);

    }

    /**
     * Метод для обновления информации об объявлении
     * @param id
     * @param properties
     * @return {@link AdsDTO}
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Обновить информацию об объявлении по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public AdsDTO updateAds(@PathVariable Integer id,
                            @RequestBody CreateAdsDTO properties) {
        return adsService.updateAds(id, properties);
    }

    /**
     * Метод для получения информации об объявлениях пользователя
     * @return {@link AllAdsDTO}
     */
    @GetMapping("/me")
    @Operation(summary = "Получть информацию об объявлениях пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public AllAdsDTO getUserAds() {
        return adsService.getUserAds();
    }

    /**
     * Метод обновления изображения объявления
     * @param id
     * @param image
     */
    @PatchMapping("/{id}/image")
    @Operation(summary = "Обновить картинки объявлении по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public void updateAdsImage(@PathVariable Integer id,
                                 @RequestPart(name = "image") MultipartFile image) {
        adsService.updateAdsImage(id, image);
    }

    /**
     * Метод для получения комментария
     * @param id
     * @return {@link GetCommentDTO}
     */
    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев по id объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public GetCommentDTO getCommentsByAdsId (@PathVariable Integer id) {;

        return commentService.getCommentsByAdsId(id);
    }

    /**
     * Метод для добавления комментария
     * @param id
     * @param createCommentDTO
     * @return {@link CommentDTO}
     */
    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public CommentDTO addComment (@PathVariable Integer id, @RequestBody CreateCommentDTO createCommentDTO) {

        return commentService.addComment(id, createCommentDTO);
    }

    /**
     * Метод для удаления комментария
     * @param adsId
     * @param commentId
     */
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария по id и id объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public void deleteComment (@PathVariable Integer adsId, @PathVariable Integer commentId) {
        commentService.deleteComment(adsId, commentId);

    }

    /**
     * Метод для обновления комментария
     * @param adsId
     * @param commentId
     * @param createCommentDTO
     * @return {@link CommentDTO}
     */
    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария по id и id объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public CommentDTO updateComment(@PathVariable Integer adsId,
                                @PathVariable Integer commentId,
                                @RequestBody CreateCommentDTO createCommentDTO) {
        return commentService.updateComment(adsId, commentId, createCommentDTO);
    }

    /**
     * Метод для получения изображения объявления
     * @param id
     * @return byte[]
     */
    @GetMapping("/image/{id}")
    @Operation(summary = "Получение картинки объявления")
    @ApiResponse(responseCode = "200",description = "OK")
    public byte[] getImage(@PathVariable String id) {
        return adsService.getImage(id);
    }

}
