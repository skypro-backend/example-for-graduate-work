package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.User;
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
  public ResponseEntity<Collection<User>> getUsers() {
    return ResponseEntity.ok(userService.getUsers());
  }

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
  public ResponseEntity<User> getUser(
      @Min(value = 1, message = "Идентификатор должен быть больше 0")
      @NotBlank(message = "id не должен быть пустым")
      @PathVariable(required = true, name = "id") int id) {
    return ResponseEntity.ok(userService.getUser(id));
  }

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
  public ResponseEntity<User> updateUser(
      @Parameter(description = "first Name", example = "someFirstName")
      @RequestParam(required = false, name = "firstName")
      @NotBlank(message = "firstName не должен быть пустым") String firstName,

      @Parameter(description = "last Name", example = "someLastName")
      @RequestParam(required = false, name = "lastName")
      @NotBlank(message = "lastName не должен быть пустым") String lastName,

      @Parameter(description = "phone", example = "somePhone")
      @RequestParam(required = false, name = "phone")
      @NotBlank(message = "phone не должен быть пустым") String phone,

      @Parameter(description = "first Name", example = "someId")
      @RequestParam(required = true, name = "id")
      @NotBlank(message = "id не должен быть пустым")
      @Min(value = 1, message = "Идентификатор должен быть больше 0") int id,

      @Parameter(description = "email", example = "someEmail")
      @RequestParam(required = false, name = "email")
      @NotBlank(message = "email не должен быть пустым") String email) {
    return ResponseEntity.ok(userService.updateUser(id, firstName, lastName, phone, email));
  }


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
  public void setPassword(
      @Parameter(description = "New password")
      @RequestParam(required = true,name = "newPassword")
      @NotBlank(message = "newPassword не должен быть пустым") String newPassword,

      @Parameter(description = "current password")
      @RequestParam(required = true, name = "currentPassword")
      @NotBlank(message = "currentPassword не должен быть пустым") String currentPassword) {
    userService.setPassword(newPassword, currentPassword);
  }

}