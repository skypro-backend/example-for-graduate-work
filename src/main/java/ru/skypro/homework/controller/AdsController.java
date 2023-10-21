package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления",
            description = "Удаление объявления по идентификационному номеру авторизованным пользователем")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<Void> removeAd(@PathVariable("id") Integer id) {
        // Реализация удаления объявления
        return ResponseEntity.noContent().build();
    }
}



