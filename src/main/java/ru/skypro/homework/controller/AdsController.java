package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "Операции с объявлениями")
public class AdsController {

    private final List<Ad> adList = new ArrayList<>();
    private int nextId = 1;

    @Operation(summary = "Получение всех объявлений")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        Ads ads = new Ads();
        ads.setCount(adList.size());
        ads.setResults(adList);
        return ResponseEntity.ok(ads);
    }

    @Operation(summary = "Добавление нового объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestBody CreateOrUpdateAd createOrUpdateAd) {
        Ad ad = new Ad();
        ad.setPk(nextId++);
        ad.setTitle(createOrUpdateAd.getTitle());
        ad.setPrice(createOrUpdateAd.getPrice());
        ad.setImage("placeholder.jpg"); // временно
        ad.setAuthor(1); // пока статично

        adList.add(ad);
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    @Operation(summary = "Обновление объявления по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable int id,
                                       @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        for (Ad ad : adList) {
            if (ad.getPk() == id) {
                ad.setTitle(createOrUpdateAd.getTitle());
                ad.setPrice(createOrUpdateAd.getPrice());
                return ResponseEntity.ok(ad);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Удаление объявления по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable int id) {
        Iterator<Ad> iterator = adList.iterator();
        while (iterator.hasNext()) {
            Ad ad = iterator.next();
            if (ad.getPk() == id) {
                iterator.remove();
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}