package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.LoginReq;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.security.UserDetailServiceImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Авторизация")
@RestController
public class AuthController {

    private final UserDetailServiceImpl userDetailService;

    public AuthController(
        @Qualifier("UserDetailServiceImpl") UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }
    @Operation(summary = "Авторизация на сайте")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
                @Content(
                    schema = @Schema(ref = "#/components/schemas/LoginReq"))
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
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req) {
        if (userDetailService.login(req.getUsername(), req.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Регистрация на сайте")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
                @Content(
                    schema = @Schema(ref = "#/components/schemas/RegisterReq"))
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
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq req) {
        if (userDetailService.register(req)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
