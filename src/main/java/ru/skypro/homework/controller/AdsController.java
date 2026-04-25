package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.skypro.homework.entities.Ad;
import ru.skypro.homework.service.AdService;

import java.util.List;

@Slf4j
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Объявления", description = "Управление объявлениями")
public class AdsController {

    private AdService adService;

    @Operation(summary = "получение всех объявлений", tags = {"Объявления"})
    @GetMapping("")
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAll());
    }

    @Operation(summary = "Создание объявления", tags = {"Объявления"})
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addAd(@RequestPart("properties") CreateOrUpdateAd properties,
                                   @RequestPart("image") MultipartFile image) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            Object principal = auth.getPrincipal();
            adService.addAd(properties, image, ((UserDetails) principal).getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body("объявление создано");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("залогинься");
    }

    @Operation(summary = "Получить объявление", tags = {"Объявления"})
    @GetMapping("/{id}")
    public ResponseEntity<?> getAds(@RequestParam("id") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            if (adService.existAd(id)) {
                return ResponseEntity.ok(adService.getAd(id));
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("такого объявления не найдено");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("залогинься");
    }

    @Operation(summary = "Удалить объявление", tags = {"Объявления"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@RequestParam("id") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            if (adService.existAd(id)) {
                adService.deleteAd(id);
                return ResponseEntity.ok("объявление удалено");
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("такого объявления не найдено");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("залогинься");
    }

    @Operation(summary = "обновить объявление", tags = {"Объявления"})
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateADs(@RequestParam("id") long id, @RequestBody CreateOrUpdateAd ad,
                                       @AuthenticationPrincipal UserDetails currentUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            if (adService.existAd(id)) {
                if (adService.postedByUser(id, currentUser)) {
                    adService.updatePost(id, ad);
                    return ResponseEntity.ok("объявление обновленно");
                } else
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("пользователь не является владельцем объявления");
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("такого объявления не найдено");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("залогинься");
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя", tags = {"Объявления"})
    @GetMapping("/me")
    public ResponseEntity<?> getAdsMe(@AuthenticationPrincipal UserDetails userDetails) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return ResponseEntity.ok(adService.userAds(userDetails));
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("залогинься");
    }

    @Operation(summary = "Обновление картинки объявления", tags = {"Объявления"})
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@RequestParam("id") long id, @RequestBody MultipartFile image,
                                         @AuthenticationPrincipal UserDetails currentUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            if (adService.existAd(id)) {
                if (adService.postedByUser(id, currentUser)) {
                    adService.updatePostImage(id, image);
                    return ResponseEntity.ok("фото объявления обновленно");
                } else
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("пользователь не является владельцем объявления");
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("такого объявления не найдено");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("залогинься");
    }

}
