package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.service.AuthService;

//@Slf4j
//@CrossOrigin(value = "http://localhost:3000")
//@Tag(name = "Регистрация", description = "контроллер для работы с регистрацией пользователя")
//@RestController
//@RequiredArgsConstructor
//
//@RequestMapping("/register")
//public class RegisterController {
//
//    private final AuthService authService;
//    @Operation(
//            summary = "Регистрация пользователя",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "201",
//                            description = "Created"
//                    ),
//                    @ApiResponse(
//                            responseCode = "400",
//                            description = "Bad Request"
//                    )
//            }
//    )
//    @PostMapping("")
//    public ResponseEntity<?> register(@RequestBody RegisterDto register) {
//        Role role = register.getRole();
//        if (authService.register(register, role)) {
//            return ResponseEntity.status(HttpStatus.CREATED).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
//}
