package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;

import java.util.List;

/**
 * <h2>Advertisements controller to manage ads</h2>
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")

@RestController
public class Advertisements {


    /**
     * GET /ads Получение всех объявлений
     *
     * @return Ads
     */
    @GetMapping("/ads")
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(new AdsDto());

    }

    /**
     * Добавление объявления
     *
     * @return Ad
     */
    @PostMapping("/ads")

    public ResponseEntity<AdDto> addAd(@RequestBody AdDto ad) {
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

    /**
     * GET /ads/{id} Получение информации об объявлении
     */
    @GetMapping("/ads/{id}")
    public ResponseEntity<AdDto> getAds(@PathVariable long id) {
        return new ResponseEntity<>(new AdDto(), HttpStatus.OK);
    }

    /**
     * DELETE /ads/{id} <h2>Удаление объявления</h2>
     *
     * @param id identifier
     * @return response code '204':
     * description: No Content
     * '401':
     * description: Unauthorized
     * '403':
     * description: Forbidden
     * '404':
     * description: Not found
     */
    @DeleteMapping("/ads/{id}")
    public ResponseEntity<HttpStatus> removeAd(@Parameter(name = "id", description = "advertisement identifier")
                                               @PathVariable long id) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /*
     * PATCH /ads/{id} Обновление информации об объявлении
     *
     */

    /**
     * @param id advertisement identifier
     * @return updated advertisement with response code as follows: '200':
     * description: OK
     * content:
     * application/json:
     * schema:
     * $ref: '#/components/schemas/Ad'
     * '403':
     * description: Forbidden
     * '401':
     * description: Unauthorized
     * '404':
     * description: Not found
     */
    @PatchMapping("/ads/{id}")

    ResponseEntity<AdDto> updateAds(@Parameter(name = "id", description = "advertisement identifier")
                                 @PathVariable long id) {
        return new ResponseEntity<>(new AdDto(), HttpStatus.OK);

    }


    /**
     * GET /ads/me <h2>Получение объявлений авторизованного пользователя</h2>
     *
     * @param id user identifier
     * @return list of advertisements
     */

    @GetMapping("/ads/me")
     public ResponseEntity<AdsDto> getAdsMe(@Parameter(name = "id", description = "user identifier")
                                        @PathVariable long id) {
        return new ResponseEntity<>(new AdsDto(), HttpStatus.OK);

    }


    /**
     * <h2>PATCH /ads/{id}/image Обновление картинки объявления</h2>
     *
     * @param id    advertisement identifier
     * @param image new photo
     * @return updated picture
     */
    @PatchMapping("/ads/{id}/image")

    public ResponseEntity<List<byte[]>> updateImage(@Parameter(name = "id", description = "user identifier")
                                                    @PathVariable(name = "id") long id,
                                                    @RequestBody MultipartFile image) {
        return new ResponseEntity<List<byte[]>>(HttpStatus.OK);

    }


}
