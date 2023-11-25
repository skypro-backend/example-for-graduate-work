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


@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final CommentService commentService;

    @GetMapping
    @Operation(summary = "Получить все объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    public AllAdsDTO getAllAds() {
        List<AdsDTO> results = adsService.getAllAds();
        int count = results.size();
        return new AllAdsDTO(count, results);
    }

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

    @GetMapping("/{id}")
    @Operation(summary = "Получить информацию об объявлении по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public AdsInfoDTO getAdsById(@PathVariable long id) {

        return adsService.getAdsById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить объявление по id")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public void deleteAds (@PathVariable long id) {
        adsService.deleteAds(id);

    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновить информацию об объявлении по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public AdsDTO updateAds(@PathVariable long id,
                            @RequestBody CreateAdsDTO properties) {
        return adsService.updateAds(id, properties);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об объявлениях пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public AllAdsDTO getUserAds() {
        List<AdsDTO> results = adsService.getUserAds();
        int count = results.size();
        return new AllAdsDTO(count, results);
    }

    @PatchMapping("/{id}/image")
    @Operation(summary = "Обновить картинки объявлении по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public void updateAdsImage(@PathVariable long id,
                                 @RequestPart(name = "image") MultipartFile image) {
        adsService.updateAdsImage(id, image);
    }

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев по id объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public GetCommentDTO getCommentsByAdsId (@PathVariable long id) {
        List<CommentDTO> results = commentService.getCommentsByAdsId(id);
        int count = results.size();

        return new GetCommentDTO(count, results);
    }


    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public CommentDTO addComment (@PathVariable long id, @RequestBody String text) {

        return commentService.addComment(id, text);
    }

    //Удаление комментария по его id
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария по id и id объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public void deleteComment (@PathVariable long adsId, @PathVariable long commentId) {
        commentService.deleteComment(adsId, commentId);

    }
    //Обновить комментарий
    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария по id и id объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public CommentDTO updateComment(@PathVariable long adsId,
                                @PathVariable long commentId,
                                @RequestBody String text) {
        return commentService.updateComment(adsId, commentId, text);
    }

    @GetMapping("/image/{id}")
    @Operation(summary = "Получение картинки объявления")
    @ApiResponse(responseCode = "200",description = "OK")
    public byte[] getImage(@PathVariable String id) {

        return adsService.getImage(id);
    }

}
