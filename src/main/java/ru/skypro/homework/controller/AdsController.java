package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdInfoDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.pojo.Image;
import ru.skypro.homework.pojo.User;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;


@RestController
@RequestMapping("")
public class AdsController {

    private final AdsService adsService;
    private final ImageService imageService;

    private final UserService userService;

    public AdsController(AdsService adsService, ImageService imageService, UserService userService) {
        this.adsService = adsService;
        this.imageService = imageService;
        this.userService = userService;
    }

    @PostMapping(value ="/ads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdDTO> createAd(
            Authentication authentication,
            @RequestPart("adDTO") AdDTO adDTO,
            @RequestPart("imageFile") MultipartFile imageFile
    ) {
        UserDetails userDetails = userService.loadUserByUsername(authentication.getName());


        // Вызываем сервис для создания объявления
        AdDTO createdAd = adsService.createAd(adDTO, imageFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAd);
    }


    @GetMapping("/ads")
    public ResponseEntity<Map<String, Object>> getAllAds() {
        List<AdDTO> ads = adsService.getAllAds();

        Map<String, Object> response = new HashMap<>();
        response.put("count", ads.size());
        response.put("results", ads);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/ads/{id}")
    public ResponseEntity<AdInfoDTO> getAdInfo(@PathVariable ("id")Long pk) {
        AdInfoDTO adInfoDTO = adsService.getAdsInfo(pk);

        if (adInfoDTO != null) {
            return ResponseEntity.ok(adInfoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAd(@PathVariable ("id") Long pk) {
        String resultMessage = adsService.deleteAd(pk);

        if (resultMessage.equals("Объявление успешно удалено")) {
            return ResponseEntity.ok(resultMessage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdDTO> updateAd(@PathVariable("id") Long pk, @RequestBody AdDTO updatedAd) {
        AdDTO updated = adsService.updateAd(pk, updatedAd);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getUserAds(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof User) {
            Long userId = ((User) userDetails).getUserID();
            List<AdDTO> userAds = adsService.getAdsForUser(userId);

            Map<String, Object> response = new HashMap<>();
            response.put("count", userAds.size());
            response.put("results", userAds);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PostMapping(value ="/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateAdImage(
            @PathVariable("id") Long pk,
            @RequestParam("file") MultipartFile imageFile
    ) {
        try {
            Image newImage = imageService.uploadImage(imageFile);
            adsService.updateAdImage(pk, newImage);
            return ResponseEntity.ok("Изображение успешно обновлено");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не удалось обновить изображение");
        }
}
}
