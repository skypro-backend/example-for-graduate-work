package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.*;
import ru.skypro.homework.utils.MethodLog;


@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ads")


public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;

    }

    // --------------------------------------------------------------------------------------
    //Получить все объявления
    @Operation(
            tags = "Объявления",
            summary = "Получить все объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<AdsDTO> getAds() {
        log.warn("GET запрос на получение всех объявлений, метод контроллера: {}", MethodLog.getMethodName());
        return ResponseEntity.ok(adService.getAllAds());
    }
    // --------------------------------------------------------------------------------------

    // Добавить объявление

    @Operation(
            summary = "Добавление объявления",
            tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdDTO.class))),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized"),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden"),
            @ApiResponse(responseCode = "404",
                    description = "Not Found")})

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdDTO> addAd(
            @Parameter(description = "JSON for create new ad")
            @RequestPart(value = "properties", required = false) @Validated CreateOrUpdateAdDTO createOrUpdateAdDTO,
            @Parameter(description = "file detail")
            @RequestPart("image") MultipartFile image) {
        log.warn("POST запрос на добавление объявления, тело запроса: {}, метод контроллера: {}", createOrUpdateAdDTO, MethodLog.getMethodName());
        return ResponseEntity.ok(adService.addAd(createOrUpdateAdDTO, image));
    }
    // --------------------------------------------------------------------------------------


    // Получить информацию об объявлении

    @Operation(
            tags = "Объявления",
            summary = "Получить информацию об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content()
                    )
            }
    )

    @GetMapping("/{adId}")
    public ResponseEntity<ExtendedAdDTO> getAdInfo(@PathVariable long adId) {
        log.warn("GET запрос на получение объявления с ID {}, метод контроллера: {}", adId, MethodLog.getMethodName());
        return ResponseEntity.ok(adService.getAdInfo(adId));
    }
    // --------------------------------------------------------------------------------------

    // Удаалить информацию об объявлении

    @Operation(
            tags = "Объявления",
            summary = "Удалить объявление",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content()
                    )
            }
    )

    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isAuthorAd(authentication.name, #adId)")
    @DeleteMapping("{adId}")
    public ResponseEntity<Void> removeAd(@PathVariable Long adId) {
        log.warn("DELETE запрос на удаление объявления с ID  {}, метод контроллера: {}", adId, MethodLog.getMethodName());
        return ResponseEntity.ok(adService.deleteAd(adId));
    }
    // --------------------------------------------------------------------------------------

    //Обновить информацию об объявлении
    @Operation(
            tags = "Объявления",
            summary = "Обновить информацию об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content()
                    )
            }
    )

    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isAuthorAd(authentication.name, #adId)")
    @PatchMapping(value = "/{adId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdDTO> updateAd(@PathVariable Long adId, @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        log.warn("PATCH запрос на обновление объявления с ID {},тело запроса {},  метод контроллера: {}", adId, createOrUpdateAdDTO, MethodLog.getMethodName());

        return ResponseEntity.ok(adService.patchAd(adId, createOrUpdateAdDTO));
    }
    // --------------------------------------------------------------------------------------

    //Получить объявления авторизованного пользователя

    @Operation(
            tags = "Объявления",
            summary = "Получить объявления авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content()
                    )
            }
    )

    @GetMapping("me")
    public ResponseEntity<AdsDTO> getAdsMe() {
        log.warn("GET запрос на получение объявлений активного пользователя, метод контроллера: {}", MethodLog.getMethodName());
        return ResponseEntity.ok(adService.getAllAdsByAuthor());
    }
    // --------------------------------------------------------------------------------------

    @Operation(
            tags = "Объявления",
            summary = "Обновить картинку объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Byte.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content()
                    )
            }
    )

    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isAuthorAd(authentication.name, #adId)")
    @PatchMapping(value = "/{adId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateAdImage(@PathVariable Long adId, @RequestPart MultipartFile image) {
        log.warn("PATCH запрос на обновление картинки объявления с ID {}, метод контроллера: {}", adId, MethodLog.getMethodName());
        return ResponseEntity.ok(adService.patchAdImage(adId, image));
    }

    // ======================================================================================
    // Получить комментарии объявления
    @Operation(
            tags = "Комментарии",
            summary = "Получить комментарии объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content()
                    )
            }
    )

    @GetMapping("{adId}/comments")
    public ResponseEntity<CommentsDTO> getComments(@PathVariable Long adId) {
        log.warn("GET запрос на получение комментариев объявления с ID {}, метод контроллера: {}", adId, MethodLog.getMethodName());

        return ResponseEntity.ok(adService.getComments(adId));
    }
    // --------------------------------------------------------------------------------------

    // Добавить комментарий к объявлению
    @Operation(
            tags = "Комментарии",
            summary = "Добавить комментарий к объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content()
                    )
            }
    )

    @PostMapping(value = "{adId}/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long adId, @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        log.warn("POST запрос на добавление комментария к объявлению с ID {}, тело запроса {}, метод контроллера: {}", adId, createOrUpdateCommentDTO, MethodLog.getMethodName());
        return ResponseEntity.ok(adService.addComment(adId, createOrUpdateCommentDTO));
    }
    // --------------------------------------------------------------------------------------

    // Удалить комментарий
    @Operation(
            tags = "Комментарии",
            summary = "Удалить комментарий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content()
                    )
            }
    )

    @DeleteMapping("{adId}/comments/{commentId}")
    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isAuthorComment(authentication.name, #commentId)")
    public ResponseEntity<Void> deleteComment(@PathVariable Long adId, @PathVariable Long commentId) {
        log.warn("DELETE запрос на удаление комментария с ID {}, у объявления с ID {}, метод контроллера: {}", commentId, adId, MethodLog.getMethodName());
        return ResponseEntity.ok(adService.deleteComment(adId, commentId));
    }

    // --------------------------------------------------------------------------------------
    // Обновить комментарий
    @Operation(
            tags = "Комментарии",
            summary = "Обновить комментарий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content()
                    )
            }
    )

    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isAuthorComment(authentication.name, #commentId)")
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long adId, @PathVariable Long commentId, @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        log.warn("PATCH запрос на обновление комментария с ID {}, у объявления с ID {}, метод контроллера: {}", commentId, adId, MethodLog.getMethodName());

        return ResponseEntity.ok(adService.patchComment(adId, commentId, createOrUpdateCommentDTO));
    }
    // --------------------------------------------------------------------------------------
}