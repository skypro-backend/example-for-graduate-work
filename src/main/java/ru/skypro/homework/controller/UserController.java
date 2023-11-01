package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {



  @PostMapping("/set_password")
  @Operation(
          summary = "Обновление пароля",
          responses = {
                  @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(hidden = true))),
                  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
                  @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(hidden = true))),

          }
  )
  public ResponseEntity<NewPassword> updatePassword(@RequestBody NewPassword newPassword) {
    return ResponseEntity.ok(new NewPassword());
  }

  @GetMapping("/me")
  @Operation(
          summary = "Получение информации об авторизованном пользователе",
          responses = {
    @ApiResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = User.class)
            )),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
  }
  )
  public ResponseEntity<User> getUser() {
    return ResponseEntity.ok(new User());
  }


  @PatchMapping("/me")
  @Operation(
          summary = "Обновление информации об авторизованном пользователе",
          responses = {
                  @ApiResponse(responseCode = "200",
                          content = @Content(
                                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                                  schema = @Schema(implementation = User.class)
                          )),
                  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
          }
  )
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    return ResponseEntity.ok(new User());
  }



  @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(
          summary = "Обновление аватара авторизованного пользователя",
          responses = {
                  @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(hidden = true))),
                  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
          }
  )
  public ResponseEntity<UpdateUser> updateUserImage(@RequestParam MultipartFile image) {
    return ResponseEntity.ok(new UpdateUser());
  }
}
