package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.io.IOException;
import java.util.logging.Logger;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    @GetMapping("/ads")
    public ResponseEntity<Ads> getAllAds() {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adMapper.mapToListOfDTO(adRepository.findAll()));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/ads", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<?> postAd(@RequestPart(value = "properties", required = true) CreateOrUpdateAd properties,
                                    @RequestPart("image") MultipartFile image,
                                    Authentication authentication) throws IOException {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Image imageToDB = imageService.uploadImage(image);
        if (adService.createOrUpdateAd(properties, user, imageToDB)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/ads/{id}")
    public ExtendedAd getAdById(@PathVariable int id) {
        Ad ad = adRepository.findByPk(id);
        ExtendedAd extendedAd = adMapper.mapToExtended(ad);
        return extendedAd;
    }

    @DeleteMapping("/ads/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ads/{id}")
    public ResponseEntity<AdDTO> editAd(@RequestBody CreateOrUpdateAd ad) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new AdDTO());
    }

    @GetMapping("/ads/me")
    public ResponseEntity<Ads> getAllMyAds(Authentication authentication) {
        String username = authentication.getName();
        Integer authorId = userRepository.findByUsername(username).getId();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adMapper.mapToListOfDTO(adRepository.findAllByAuthorId(authorId)));
    }

    @PatchMapping("/ads/{id}/image")
    public ResponseEntity<String> updateAdImage(@PathVariable int id,
                                                  @RequestBody MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok(new ImageDTO().getUrl());
    }

}

