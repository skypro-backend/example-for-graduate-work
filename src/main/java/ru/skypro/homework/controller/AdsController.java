package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.projection.AdView;
import ru.skypro.homework.projection.Ads;
import ru.skypro.homework.projection.CreateOrUpdateAd;
import ru.skypro.homework.projection.ExtendedAd;
import ru.skypro.homework.service.ads.AdsService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления")
public class AdsController {
    private final AdsService adsService;

    /**
     * Этот метод обрабатывает POST-запросы на добавление объявления
     *
     * @param properties проекция, которая содержит заголовок, цену и описание объявления
     * @param image ссылка на картику объявления
     * @param authentication данные авторизированного пользователя
     * @return Возвращает информацию о созданном объявлении
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdView.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)})
    public ResponseEntity<AdDTO> createAd(@RequestPart(name = "properties") @Valid CreateOrUpdateAd properties,
                                          @RequestPart(name = "image") MultipartFile image,
                                          Authentication authentication) {
        return ResponseEntity.ok(adsService.createAd(properties, image,authentication));
    }

    /**
     * Этот метод обрабатывает GET-запросы на получение всех объявлений
     *
     * @return Возвращает список всех объявлений и их количество
     */
    @GetMapping()
    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class))})
    public Ads getAllAds() {
        return adsService.getAllAds();
    }


    /**
     * Этот метод обрабатывает GET-запросы на получение информации о конкретном объявлении
     *
     * @param id идентификатор объявления
     * @return Возвращает всю информацию об объявлении с указанным id
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExtendedAd.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
    public ResponseEntity<ExtendedAd> getAdFullInfo(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(adsService.getAdFullInfo(id));
    }

    /**
     * Этот метод обрабатывает DELETE-запросы на удаление объявления
     *
     * @param id идентификатор объявления
     * @param authentication данные авторизированного пользователя
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")})
    @PreAuthorize("hasRole('ADMIN') or hasAuthority(@adsServiceImpl.getAdFullInfo(#id).getEmail().equalsIgnoreCase(authentication.name))")
    public void deleteAd(@PathVariable("id") Integer id,Authentication authentication) {
        adsService.deleteAd(id);
    }

    /**
     * Этот метод обрабатывает PATCH-запросы на обновление информации об объявлении
     *
     * @param id идентификатор объявления
     * @param properties проекция, которая содержит заголовок, цену и описание объявления
     * @param authentication данные авторизированного пользователя
     * @return Возвращает обновленную информацию об объявлении
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdView.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasAuthority(@adsServiceImpl.getAdFullInfo(#id).getEmail().equalsIgnoreCase(authentication.name))")
    public AdView updateAd(@PathVariable("id") Integer id,
                           @RequestBody @Valid CreateOrUpdateAd properties,
                           Authentication authentication) {
        return adsService.updateAd(id, properties);
    }

    /**
     * Этот метод обрабатывает PATCH-запросы на обновление картинки в объявлении
     *
     * @param id идентификатор объявления
     * @param image ссылка на новую картику
     * @param authentication данные авторизированного пользователя
     * @return Возвращает обновленную картинку объявления
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasAuthority(@adsServiceImpl.getAdFullInfo(#id).getEmail().equalsIgnoreCase(authentication.name))")
    public String updateImage(@PathVariable("id") Integer id,
                              @RequestPart("image") MultipartFile image,
                              Authentication authentication) {
        return adsService.updateImage(id, image);
    }

    /**
     * Этот метод обрабатывает GET-запросы на получение всех объявлений авторизированного пользователя
     *
     * @param authentication данные авторизированного пользователя
     * @return Возвращает список объявлений авторизированного пользователя
     */
    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Ads.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)})
    public Ads getAllAdsByUser(Authentication authentication) {
        return adsService.getAllAdsByUser(authentication);
    }
}
