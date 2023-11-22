package ru.skypro.homework.controller;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.service.AdService;

import java.util.Collection;

@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@Validated
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping
    public void createAd(@RequestBody AdDTO adDTO) {
        adService.createAd(adDTO);

    }

    @GetMapping
    public Collection<AdDTO> all() {
        return adService.getAll();
    }

    @GetMapping("{id}")
    public AdDTO findAdById(@PathVariable int id) {
        return adService.findAd(id);
           }
}
