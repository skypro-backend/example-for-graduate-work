package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;

import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {
    //Получение всех объявлений
    @GetMapping("/")
    public List<AdsDTO> getAllAds() {
        return null;
    }

    //Добавление объявления
    @PostMapping("/")
    public AdsDTO addAds(@RequestBody AdsDTO adsDTO) {
        return null;
    }

    //Получение информации об объявлении по id
    @GetMapping("/{id}")
    public AdsDTO getAds(@PathVariable long id) {
        return null;
    }

    //Удаление объявления по id
    @DeleteMapping("/{id}")
    public void deleteAds (@PathVariable long id) {

    }

    //Обновить информацию об объявлении
    @PatchMapping("/{id}")
    public AdsDTO updateAdsInfo(@PathVariable long id) {
        return null;
    }

    //Получение информации об объявлениях пользователя
    @GetMapping("/me")
    public List<AdsDTO> getUserAds() {
        return null;
    }

    //Обновить изображение объявления
    @PatchMapping("/{id}/image")
    public AdsDTO updateAdsImage(@PathVariable long id) {
        return null;
    }

    //Получение комментариев об объявлении
    @GetMapping("/{id}/comments")
    public CommentDTO getComment (@PathVariable long id) {
        return null;
    }

    //Добавление комментариев об объявлении
    @PostMapping("/{id}/comments")
    public CommentDTO addComment (@PathVariable long id, @RequestBody String text) {
        return null;
    }

    //Удаление комментария по его id
    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment (@PathVariable long commentId) {

    }
    //Обновить комментарий
    @PatchMapping("/{adId}/comments/{commentId}")
    public AdsDTO updateComment(@PathVariable long id, @RequestBody String text) {
        return null;
    }

}
