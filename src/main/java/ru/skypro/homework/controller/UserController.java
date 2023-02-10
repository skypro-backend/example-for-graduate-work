package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Collection;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
//@Tag(name = "Объявления")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class UserController {


  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

    @Operation(summary = "addUser")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            )
    })
    @PatchMapping()
    public ResponseEntity<UserDTO> addUser(
            @RequestBody
            @NotBlank(message = "addUser не должен быть пустым") UserDTO userDto) {
        return ResponseEntity.ok(userService.addUser(userDto));
    }
  @Operation(summary = "getUsers")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK"
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
  @GetMapping(value = "/me")
  public ResponseEntity<Collection<UserDTO>> getUsers() {
    return ResponseEntity.ok(userService.getUsers());
  }

  @Operation(summary = "getUser")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK"
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
  @GetMapping(value = "{id}")
  public ResponseEntity<UserDTO> getUser(
      @Min(value = 1, message = "Идентификатор должен быть больше 0")
      @NotBlank(message = "id не должен быть пустым")
      @PathVariable(required = true, name = "id") int id) {
    return ResponseEntity.ok(userService.getUser(id));
  }
  @Operation(summary = "updateUser")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK"
      ),
      @ApiResponse(
          responseCode = "204",
          description = "No Content"
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized"
      ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden"
      )
  })
  @PatchMapping(value = "/me")
  public ResponseEntity<UserDTO> updateUser(
      @RequestBody
      @NotBlank(message = "updateUser не должен быть пустым") UserDTO userDto) {
    return ResponseEntity.ok(userService.updateUser(userDto));
  }

  @Operation(summary = "setPassword")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK"
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
  @PostMapping(value = "/setPassword")
  public ResponseEntity<String> setPassword(
      @RequestBody
      @NotBlank(message = "newPassword не должен быть пустым") String newPassword,

      @RequestBody
      @NotBlank(message = "currentPassword не должен быть пустым") String currentPassword) {
    String s = userService.setPassword(newPassword, currentPassword);
    return ResponseEntity.ok(s);
  }

}