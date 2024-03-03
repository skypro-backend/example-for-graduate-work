package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.listing.CreateOrUpdateListing;
import ru.skypro.homework.dto.listing.ExtendedListingDTO;
import ru.skypro.homework.dto.listing.ListingDTO;
import ru.skypro.homework.dto.listing.ListingsDTO;
import ru.skypro.homework.service.impl.ImageServiceImpl;
import ru.skypro.homework.service.impl.ListingsServiceImpl;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class ListingsController {

    private final ListingsServiceImpl listingService;
    private final ImageServiceImpl imageService;

    @GetMapping()
    @Operation(summary = "Получение всех объявлений", description = "getAllListings")
    public ResponseEntity<ListingsDTO> getAllListings() {
        return ResponseEntity.ok((ListingsDTO) listingService.getAllListings());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления", description = "addListing")
    public ResponseEntity<ListingDTO> addListing(@RequestPart(value = "properties", required = false) CreateOrUpdateListing createOrUpdateListingDTO,
                                                 @RequestPart("image") MultipartFile image,
                                                 Authentication authentication) throws IOException {
        return ResponseEntity.ok(listingService.addListing(createOrUpdateListingDTO, image, authentication));
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение информации об объявлении", description = "getListing")
    public ResponseEntity<ExtendedListingDTO> getListing(@PathVariable long id) {
        return ResponseEntity.ok(listingService.getListing(id));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление объявления", description = "removeListing")
    public ResponseEntity<?> deleteListing(@Parameter(description = "ID объявления")
                                               @PathVariable long id, Authentication authentication) throws AccessDeniedException {
        listingService.deleteListing(id, authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("{id}")
    @Operation(summary = "Обновление информации об объявлении", description = "updateInfoListing")
    public ResponseEntity<ListingDTO> updateListingInformation(@Parameter(description = "ID объявления") @PathVariable long id,
                                                               @RequestBody CreateOrUpdateListing createOrUpdateListing,
                                                               Authentication authentication) throws AccessDeniedException {
        return ResponseEntity.ok(listingService.updateListing(id, createOrUpdateListing, authentication));
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя", description = "getListingsMe")
    public ResponseEntity<ListingsDTO> getListingsFromAuthorizedUser(Authentication authentication) {
        return ResponseEntity.ok(listingService.getListingsMe(authentication));
    }

    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления", description = "updateImage")
    public ResponseEntity<?> updateImage(@Parameter(description = "ID объявления") @PathVariable("id") long id,
                                         @RequestPart("image") MultipartFile image, Authentication authentication) throws IOException {
        listingService.updateListingImage(id, image, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE
    })
    @Operation(summary = "Получение картинки объявления", description = "getListingsImage")
    public ResponseEntity<byte[]> getListingsImage(@PathVariable("id") long id) {
        byte[] image = imageService.getImage(id).getData();
        return ResponseEntity.ok(image);
    }
}
