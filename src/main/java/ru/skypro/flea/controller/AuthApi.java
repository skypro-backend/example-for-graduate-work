package ru.skypro.flea.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.skypro.flea.dto.LoginDto;
import ru.skypro.flea.dto.RegisterDto;

import javax.validation.Valid;

@Validated
public interface AuthApi {

    @Operation(summary = "User authorization")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            )
    })
    @RequestMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    ResponseEntity<Void> login(@RequestBody @Valid LoginDto login);

    @Operation(summary = "User registration")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @RequestMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    ResponseEntity<Void> register(@RequestBody @Valid RegisterDto register);

}
