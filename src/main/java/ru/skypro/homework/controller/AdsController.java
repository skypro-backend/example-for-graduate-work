package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.service.AdsService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@AllArgsConstructor
@RestController
public class AdsController {
    private final AdsService adsService;



    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Comment.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found"
            )
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<AdsDto> getAds(@PathVariable(name = "id")
                                               @NotBlank(message = "id не должен быть пустым")
                                               @Min(value = 1, message = "Идентификатор должен быть больше 0")
                                               @Parameter(description = "Идентификатор комментария",
                                                       example = "1") int id) {
        return ResponseEntity.ok(adsService.getAds( id));
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Comment.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found"
            )
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable(name = "id")
                          @NotBlank(message = "id не должен быть пустым")
                          @Min(value = 1, message = "Идентификатор должен быть больше 0")
                          @Parameter(description = "Идентификатор объявления",
                                  example = "1") int id) {
       return  ResponseEntity.ok(adsService.updateAds( id));
    }
}
