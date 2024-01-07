package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.listing.CreateOrUpdateListing;
import ru.skypro.homework.dto.listing.ExtendedListingDTO;
import ru.skypro.homework.dto.listing.ListingDTO;
import ru.skypro.homework.dto.listing.ListingsDTO;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class ListingsController {

    @GetMapping()
    public ResponseEntity<ListingsDTO> getAllListings() {
        return ResponseEntity.ok(new ListingsDTO());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ListingDTO> addListing(@RequestBody CreateOrUpdateListing properties, @RequestBody MultipartFile image) {
        return new ResponseEntity<>(new ListingDTO(), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ExtendedListingDTO> getInformationByListing(@PathVariable Integer id) {
        return ResponseEntity.ok(new ExtendedListingDTO());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteListing(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ListingDTO> updateListingInformation(@PathVariable Integer id, @RequestBody CreateOrUpdateListing properties) {
        return ResponseEntity.ok(new ListingDTO());
    }

    @GetMapping("/me")
    public ResponseEntity<ListingsDTO> getListingsFromAuthorizedUser() {
        return ResponseEntity.ok(new ListingsDTO());
    }

    @PutMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateListingPicture(@PathVariable Integer id, @RequestBody MultipartFile image) {
        HttpHeaders headers = new HttpHeaders();
        byte[] data = {};
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(data);
    }
}
