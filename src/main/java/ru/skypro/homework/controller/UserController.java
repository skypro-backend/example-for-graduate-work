package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.loger.FormLogInfo;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class UserController {


  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Установить новый пароль")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content =
          @Content(
              array = @ArraySchema(schema = @Schema(implementation = NewPassword.class)))
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found",
          content = @Content(schema = @Schema())
      )
  })
  @PostMapping(value = "/setPassword")
  public ResponseEntity<NewPassword> setPassword(
      @RequestBody
      @NotBlank(message = "newPassword не должен быть пустым") NewPassword newPassword) {
    log.info(FormLogInfo.getInfo());
    NewPassword newPasswordDTO = userService.setPassword(newPassword);
    return ResponseEntity.ok(newPasswordDTO);
  }

  @Operation(summary = "Получить пользователя")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found",
          content = @Content(schema = @Schema())
      )
  })
  @GetMapping(value = "/me")
  public ResponseEntity<UserDTO> getUser(Authentication authentication) {
    log.info(FormLogInfo.getInfo());
    return ResponseEntity.ok(userService.getUser(authentication));
  }

  @Operation(summary = "Обновить пользователя")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))
      ),
      @ApiResponse(
          responseCode = "204",
          description = "No Content",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found",
          content = @Content(schema = @Schema())
      )
  })
  @PatchMapping(value = "/me")
  public ResponseEntity<UserDTO> updateUser(
      @RequestBody
      @NotBlank(message = "updateUser не должен быть пустым") UserDTO userDto) {
    log.info(FormLogInfo.getInfo());
    return ResponseEntity.ok(userService.updateUser(userDto));
  }

  @Operation(summary = "Обновить изображение пользователя")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(
              array = @ArraySchema())
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found",
          content = @Content(schema = @Schema())
      )
  })
  @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<MultipartFile> updateUserImage(@RequestParam MultipartFile image,
      Authentication authentication) {
    log.info(FormLogInfo.getInfo());
    userService.updateUserImage(image, authentication);
    return ResponseEntity.ok().build();
  }


}