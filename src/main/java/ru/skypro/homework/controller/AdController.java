package ru.skypro.homework.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(path = "/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdController {

    @GetMapping
    public AdsDto getAllAds() {
        return new AdsDto();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AdDto addAd(@RequestPart(name = "properties") CreateAdDto ad,
                       @RequestPart(name = "image") MultipartFile file) {
        return new AdDto();
    }

    @GetMapping(path = "/{id}")
    public ExtendedAdDto getAdById(@PathVariable(value = "id") Integer id) {
        return new ExtendedAdDto();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAdById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(path = "/{id}")
    public AdDto updateAdById(@PathVariable(value = "id") Integer id,
                              @RequestBody CreateAdDto ad) {
        return new AdDto();
    }

    @GetMapping(path = "/me")
    public List<AdDto> getAuthorizedUserAds() {
        return List.of(new AdDto[]{});
    }

    @PatchMapping(
            path = "/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public Resource updateImageByAdId(@PathVariable(value = "id") Integer id,
                                      @RequestPart(name = "image") MultipartFile file) throws IOException {
        return new ByteArrayResource(Files.readAllBytes(Paths.get("mto.jpg")));
    }

}
