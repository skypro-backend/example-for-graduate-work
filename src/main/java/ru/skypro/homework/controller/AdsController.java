package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skypro.homework.record.AdRecord;
import ru.skypro.homework.service.AdService;

@Controller
@RequestMapping("/ads")
@Tag(name = "Объявления")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

  private final AdService adService;

  public AdsController(AdService adService) {
    this.adService = adService;
  }

  @Operation(summary = "Получить все объявления")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  schema = @Schema(ref = "#/components/schemas/ResponseWrapper«Ads»"))
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
  @GetMapping
  public ResponseEntity<Map<String, Object>> getALLAds() {
    return ResponseEntity.ok(adService.getALLAds());
  }

  @Operation(summary = "Добавить объявление")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  array = @ArraySchema(schema = @Schema(implementation = AdRecord.class)))
          }
      ),
      @ApiResponse(
          responseCode = "201",
          description = "Created"
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
  @PostMapping
  public ResponseEntity<AdRecord> createAds(@RequestBody AdRecord adRecord) {
    return ResponseEntity.ok(adService.addAds(adRecord));
  }

  @Operation(summary = "Получить объявления по заданным параметрам")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  schema = @Schema(ref = "#/components/schemas/ResponseWrapper«Ads»"))
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
  @GetMapping("/me")
  public ResponseEntity<Map<String, Object>> getAdsMe(
      @RequestParam(required = false) boolean authenticated,
      @RequestParam(required = false) String authorities,
      @RequestBody(required = false) Object credentials,
      @RequestBody(required = false) Object details,
      @RequestBody(required = false) Object principal
  ) {
    return ResponseEntity.ok(
        adService.getAdsMe(authenticated, authorities, credentials, details, principal));
  }

}
