package ru.skypro.homework.controller;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.PictureDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.service.impl.AdsService;

/**
 * Контроллер для работы с объявлениями
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")

public class AdsController {

    private AdsService AdsService;
    private AdsDto ads;
    private AdsDto PictureDto;

    public AdsController(AdsService adsService) {
    }

    @Operation(
            summary = "получение всех объявлений",
            tags= "объявления"
    )
    @GetMapping
    public AdsDto getAds(@RequestBody AdsDto ads){

        return new AdsDto();
    }
    @Operation(
            summary = "добавление объявлений",
            tags= "объявления"
    )
    @PostMapping
    public AdsService addAds(@RequestBody Ads ads){

        return AdsService;
    }

    @Operation(
            summary = "получение информации об объявлении",
            tags= "объявления"
    )
    @GetMapping("/{id}")
    public AdsDto getAds(@PathVariable("id") int id){
      
        return ads;
    }
    @Operation(
            summary = "удаление объявлении",
            tags= "объявления"

    )

    @DeleteMapping("/{id}")
    public AdsDto deleteByIdDto(@PathVariable("id") int id){
        return ads;
    }
    @Operation(
            summary = "обновление информации об объявлении",
            tags= "объявления"
    )
    @PatchMapping("/{id}")
    public CreateAdsDto updateCreateAdsDto(@RequestBody CreateAdsDto Ads) {
        return new CreateAdsDto();
    }
    @Operation(
            summary = "Получение информации авторизованного пользователя",
            tags= "объявления"
    )

    @GetMapping("/me")
    public AdsDto getLoginUserAds(@RequestBody AdsDto ads){

        return new AdsDto();
    }
    @Operation(
            summary= "Обновление картинки объявления (по id)",
            tags= "объявления"
    )

    @PatchMapping("/{id}/image")
    public AdsDto updateAdPicture(@PathVariable("id") int idPicture, PictureDto pictureDto){
        return PictureDto;
    }





    }













