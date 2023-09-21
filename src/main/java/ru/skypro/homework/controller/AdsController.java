package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.AdsService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {
    private final AdsService adsService;

    @GetMapping
    public ResponseEntity<?> ads() {
//запрос в сервис
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createAd(@RequestBody CreateOrUpdateAd createOrUpdateAd, @RequestBody String image) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<?> adDto(@PathVariable Integer id) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAd(@PathVariable Integer id) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }
    @PatchMapping("{id}")
    public ResponseEntity<?> UpdateAd(@RequestBody CreateOrUpdateAd createOrUpdateAd, @PathVariable Integer id) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }
    @GetMapping("/me")
    public ResponseEntity<?> myAds() {
//запрос в сервис
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/image")
    public ResponseEntity<?> UpdateImage(@RequestBody String image, @PathVariable Integer id) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }
    /**
     * Контроллеры для комментариев
     */
    @GetMapping("{id}/comments")
    public ResponseEntity<?> comments(@PathVariable Integer id) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }
    @PostMapping("{id}/comments")
    public ResponseEntity<?> createComment(@RequestBody CreateOrUpdateComment createOrUpdateComment, @PathVariable Integer id) {
        //запрос в сервис
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id, @PathVariable Integer commentId) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }
    @PatchMapping("{id}/comments/{commentId}")
    public ResponseEntity<?> UpdateComment(@PathVariable Integer id,
                                           @PathVariable Integer commentId,
                                           @RequestBody CreateOrUpdateAd createOrUpdateAd) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }

}
