package ru.skypro.homework.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;
import java.io.IOException;

/**
 * REST-контроллер работы с объявлениями.
 */
@RestController
@RequestMapping("/ads")
@Validated
public class AdController {

    private final AdService adService;
    private final CommentService commentService;

    public AdController(
            AdService adService,
            CommentService commentService
    ) {
        this.adService = adService;
        this.commentService = commentService;
    }

    /**
     * Возвращает все объявления.
     *
     * @return список объявлений
     */
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(
                adService.getAllAds()
        );
    }

    /**
     * Возвращает одно объявление.
     *
     * @param id идентификатор
     * @return полное объявление
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                adService.getAd(id)
        );
    }

    /**
     * Создаёт объявление с изображением.
     *
     * @param properties данные объявления
     * @param image изображение
     * @param authentication данные авторизации
     * @return созданное объявление
     * @throws IOException при ошибке чтения файла
     */
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Ad> createAd(
            @Valid
            @RequestPart("properties")
            CreateOrUpdateAd properties,

            @RequestPart("image")
            MultipartFile image,

            Authentication authentication
    ) throws IOException {

        if (!isValidImage(image)) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        Ad createdAd = adService.createAd(
                authentication.getName(),
                properties,
                image.getBytes(),
                image.getContentType()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAd);
    }

    /**
     * Возвращает объявления текущего пользователя.
     *
     * @param authentication данные авторизации
     * @return объявления пользователя
     */
    @GetMapping("/me")
    public ResponseEntity<Ads> getMyAds(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                adService.getMyAds(
                        authentication.getName()
                )
        );
    }

    /**
     * Обновляет данные объявления.
     *
     * @param id идентификатор
     * @param properties новые данные
     * @param authentication данные авторизации
     * @return обновлённое объявление
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(
            @PathVariable Long id,
            @Valid @RequestBody
            CreateOrUpdateAd properties,
            Authentication authentication
    ) {
        Ad updatedAd = adService.updateAd(
                id,
                authentication.getName(),
                getRole(authentication),
                properties
        );

        return ResponseEntity.ok(updatedAd);
    }

    /**
     * Удаляет объявление.
     *
     * @param id идентификатор
     * @param authentication данные авторизации
     * @return ответ без тела
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(
            @PathVariable Long id,
            Authentication authentication
    ) {
        adService.deleteAd(
                id,
                authentication.getName(),
                getRole(authentication)
        );

        return ResponseEntity
                .noContent()
                .build();
    }

    /**
     * Обновляет изображение объявления.
     *
     * @param id идентификатор
     * @param image новое изображение
     * @param authentication данные авторизации
     * @return новое изображение
     * @throws IOException при ошибке чтения
     */
    @PatchMapping(
            value = "/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<byte[]> updateImage(
            @PathVariable Long id,
            @RequestPart("image") MultipartFile image,
            Authentication authentication
    ) throws IOException {

        if (!isValidImage(image)) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        adService.updateImage(
                id,
                authentication.getName(),
                getRole(authentication),
                image.getBytes(),
                image.getContentType()
        );

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_TYPE,
                        image.getContentType()
                )
                .body(image.getBytes());
    }

    /**
     * Возвращает изображение объявления.
     *
     * <p>Этого метода нет отдельной строкой в исходной
     * спецификации, но он необходим для URL,
     * возвращаемого в поле image.</p>
     *
     * @param id идентификатор
     * @return изображение
     */
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(
            @PathVariable Long id
    ) {
        byte[] image = adService.getImage(id);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_TYPE,
                        adService.getImageMediaType(id)
                )
                .body(image);
    }

    private boolean isValidImage(
            MultipartFile image
    ) {
        String mediaType = image.getContentType();

        return !image.isEmpty()
                && mediaType != null
                && mediaType.startsWith("image/");
    }

    private String getRole(
            Authentication authentication
    ) {
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(authority ->
                        authority.replace("ROLE_", "")
                )
                .findFirst()
                .orElse("USER");
    }

    /**
     * Возвращает комментарии объявления.
     *
     * @param id идентификатор объявления
     * @return комментарии объявления
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                commentService.getComments(id)
        );
    }

    /**
     * Добавляет комментарий к объявлению.
     *
     * @param id идентификатор объявления
     * @param request текст комментария
     * @param authentication данные авторизации
     * @return созданный комментарий
     */
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long id,
            @Valid @RequestBody
            CreateOrUpdateComment request,
            Authentication authentication
    ) {
        Comment comment = commentService.addComment(
                id,
                authentication.getName(),
                request
        );

        return ResponseEntity.ok(comment);
    }

    /**
     * Обновляет комментарий.
     *
     * @param adId идентификатор объявления
     * @param commentId идентификатор комментария
     * @param request новый текст
     * @param authentication данные авторизации
     * @return обновлённый комментарий
     */
    @PatchMapping(
            "/{adId}/comments/{commentId}"
    )
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long adId,
            @PathVariable Long commentId,
            @Valid @RequestBody
            CreateOrUpdateComment request,
            Authentication authentication
    ) {
        Comment comment =
                commentService.updateComment(
                        adId,
                        commentId,
                        authentication.getName(),
                        getRole(authentication),
                        request
                );

        return ResponseEntity.ok(comment);
    }

    /**
     * Удаляет комментарий.
     *
     * @param adId идентификатор объявления
     * @param commentId идентификатор комментария
     * @param authentication данные авторизации
     * @return ответ без тела
     */
    @DeleteMapping(
            "/{adId}/comments/{commentId}"
    )
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long adId,
            @PathVariable Long commentId,
            Authentication authentication
    ) {
        commentService.deleteComment(
                adId,
                commentId,
                authentication.getName(),
                getRole(authentication)
        );

        return ResponseEntity.ok().build();
    }

}
