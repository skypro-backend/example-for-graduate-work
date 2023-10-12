package ru.skypro.homework.controller;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.service.PictureService;
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

    public AdsController(AdsService adsService, PictureService pictureService) {
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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public AdsDto addAds(@RequestPart("properties") CreateAdsDto ads, @RequestPart("image") MultipartFile image) {
       return new AdsDto();

    }
    @Operation(
            summary = "получение информации об объявлении",
            tags= "объявления"
    )
    @GetMapping("/{id}")
    public AdsDto getAds(@PathVariable("id") int id){
      
        return new AdsDto();
    }
    @Operation(
            summary = "удаление объявлении",
            tags= "объявления"
    )
    @DeleteMapping("/{id}")
    public void deleteByIdDto(@PathVariable("id") int id){

    }
    @Operation(
            summary = "обновление информации об объявлении",
            tags= "объявления"
    )
    @PatchMapping("/{id}")
    public CreateAdsDto updateCreateAdsDto(@RequestBody CreateAdsDto ads) {
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
    public AdsDto updateAdPicture(@PathVariable("id") int id, @RequestParam("image") MultipartFile image){
        return new AdsDto();
    }



}













