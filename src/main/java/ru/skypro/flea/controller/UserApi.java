package ru.skypro.flea.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.flea.dto.NewPasswordDto;
import ru.skypro.flea.dto.UpdateUserDto;
import ru.skypro.flea.dto.UserDto;

import javax.validation.Valid;

@Validated
public interface UserApi {

    @Operation(summary = "Password update")
    @ApiResponses(value = {
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
            )
    })
    @RequestMapping(
            value = "/users/set_password",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    ResponseEntity<Void> setPassword(@RequestBody @Valid NewPasswordDto newPassword);

    @Operation(summary = "Get authorized user info")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            )
    })
    @RequestMapping(
            value = "/users/me",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    ResponseEntity<UserDto> getUser();

    @Operation(summary = "Update authorized user info")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = UpdateUserDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            )
    })
    @RequestMapping(
            value = "/users/me",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PATCH
    )
    ResponseEntity<UpdateUserDto> updateUser(@RequestBody @Valid UpdateUserDto updateUser);

    @Operation(summary = "Update authorized user avatar")
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
            value = "/users/me/image",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            method = RequestMethod.PATCH
    )
    ResponseEntity<Void> updateUserImage(@RequestPart(name = "image") MultipartFile image);

}
