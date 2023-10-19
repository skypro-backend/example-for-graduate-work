package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.DeleteAdDto;

@RestController
@RequestMapping("/ads")
public class AdsController {
    @DeleteMapping("/{adId}")
    @Operation(summary = "Удаление объявления")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<Void> removeAd(@RequestBody DeleteAdDto deleteAdDTO) {
        // Реализация удаления объявления
        return ResponseEntity.noContent().build();
    }
}



