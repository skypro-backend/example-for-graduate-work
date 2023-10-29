/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.openapitools.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-29T13:35:35.083731400+03:00[Europe/Moscow]")
@Validated
@Tag(name = "Объявления", description = "the Объявления API")
public interface AdsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /ads : Добавление объявления
     *
     * @param properties  (required)
     * @param image  (required)
     * @return Created (status code 201)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "addAd",
        summary = "Добавление объявления",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Ad.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/ads",
        produces = { "application/json" },
        consumes = { "multipart/form-data" }
    )
    default ResponseEntity<Ad> addAd(
        @Parameter(name = "properties", description = "", required = true) @Valid @RequestPart(value = "properties", required = true) CreateOrUpdateAd properties,
        @Parameter(name = "image", description = "", required = true) @RequestPart(value = "image", required = true) MultipartFile image
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"image\" : \"image\", \"author\" : 6, \"price\" : 5, \"pk\" : 1, \"title\" : \"title\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /ads/{id}/comments : Добавление комментария к объявлению
     *
     * @param id  (required)
     * @param createOrUpdateComment  (optional)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Not found (status code 404)
     */
    @Operation(
        operationId = "addComment",
        summary = "Добавление комментария к объявлению",
        tags = { "Комментарии" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/ads/{id}/comments",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Comment> addComment(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(name = "CreateOrUpdateComment", description = "") @Valid @RequestBody(required = false) CreateOrUpdateComment createOrUpdateComment
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"createdAt\" : 1, \"authorFirstName\" : \"authorFirstName\", \"author\" : 6, \"authorImage\" : \"authorImage\", \"pk\" : 5, \"text\" : \"text\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /ads/{adId}/comments/{commentId} : Удаление комментария
     *
     * @param adId  (required)
     * @param commentId  (required)
     * @return OK (status code 200)
     *         or Forbidden (status code 403)
     *         or Unauthorized (status code 401)
     *         or Not found (status code 404)
     */
    @Operation(
        operationId = "deleteComment",
        summary = "Удаление комментария",
        tags = { "Комментарии" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/ads/{adId}/comments/{commentId}"
    )
    default ResponseEntity<Void> deleteComment(
        @Parameter(name = "adId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("adId") Integer adId,
        @Parameter(name = "commentId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("commentId") Integer commentId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /ads/{id} : Получение информации об объявлении
     *
     * @param id  (required)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Not found (status code 404)
     */
    @Operation(
        operationId = "getAds",
        summary = "Получение информации об объявлении",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ExtendedAd.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/ads/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<ExtendedAd> getAds(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"image\" : \"image\", \"authorLastName\" : \"authorLastName\", \"authorFirstName\" : \"authorFirstName\", \"phone\" : \"phone\", \"price\" : 6, \"description\" : \"description\", \"pk\" : 0, \"title\" : \"title\", \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /ads/me : Получение объявлений авторизованного пользователя
     *
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "getAdsMe",
        summary = "Получение объявлений авторизованного пользователя",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Ads.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/ads/me",
        produces = { "application/json" }
    )
    default ResponseEntity<Ads> getAdsMe(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"count\" : 0, \"results\" : [ { \"image\" : \"image\", \"author\" : 6, \"price\" : 5, \"pk\" : 1, \"title\" : \"title\" }, { \"image\" : \"image\", \"author\" : 6, \"price\" : 5, \"pk\" : 1, \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /ads : Получение всех объявлений
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllAds",
        summary = "Получение всех объявлений",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Ads.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/ads",
        produces = { "application/json" }
    )
    default ResponseEntity<Ads> getAllAds(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"count\" : 0, \"results\" : [ { \"image\" : \"image\", \"author\" : 6, \"price\" : 5, \"pk\" : 1, \"title\" : \"title\" }, { \"image\" : \"image\", \"author\" : 6, \"price\" : 5, \"pk\" : 1, \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /ads/{id}/comments : Получение комментариев объявления
     *
     * @param id  (required)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Not found (status code 404)
     */
    @Operation(
        operationId = "getComments",
        summary = "Получение комментариев объявления",
        tags = { "Комментарии" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Comments.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/ads/{id}/comments",
        produces = { "application/json" }
    )
    default ResponseEntity<Comments> getComments(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"count\" : 0, \"results\" : [ { \"createdAt\" : 1, \"authorFirstName\" : \"authorFirstName\", \"author\" : 6, \"authorImage\" : \"authorImage\", \"pk\" : 5, \"text\" : \"text\" }, { \"createdAt\" : 1, \"authorFirstName\" : \"authorFirstName\", \"author\" : 6, \"authorImage\" : \"authorImage\", \"pk\" : 5, \"text\" : \"text\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /ads/{id} : Удаление объявления
     *
     * @param id  (required)
     * @return No Content (status code 204)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not found (status code 404)
     */
    @Operation(
        operationId = "removeAd",
        summary = "Удаление объявления",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/ads/{id}"
    )
    default ResponseEntity<Void> removeAd(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PATCH /ads/{id} : Обновление информации об объявлении
     *
     * @param id  (required)
     * @param createOrUpdateAd  (optional)
     * @return OK (status code 200)
     *         or Forbidden (status code 403)
     *         or Unauthorized (status code 401)
     *         or Not found (status code 404)
     */
    @Operation(
        operationId = "updateAds",
        summary = "Обновление информации об объявлении",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Ad.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/ads/{id}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Ad> updateAds(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(name = "CreateOrUpdateAd", description = "") @Valid @RequestBody(required = false) CreateOrUpdateAd createOrUpdateAd
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"image\" : \"image\", \"author\" : 6, \"price\" : 5, \"pk\" : 1, \"title\" : \"title\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PATCH /ads/{adId}/comments/{commentId} : Обновление комментария
     *
     * @param adId  (required)
     * @param commentId  (required)
     * @param createOrUpdateComment  (optional)
     * @return OK (status code 200)
     *         or Forbidden (status code 403)
     *         or Unauthorized (status code 401)
     *         or Not found (status code 404)
     */
    @Operation(
        operationId = "updateComment",
        summary = "Обновление комментария",
        tags = { "Комментарии" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/ads/{adId}/comments/{commentId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Comment> updateComment(
        @Parameter(name = "adId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("adId") Integer adId,
        @Parameter(name = "commentId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("commentId") Integer commentId,
        @Parameter(name = "CreateOrUpdateComment", description = "") @Valid @RequestBody(required = false) CreateOrUpdateComment createOrUpdateComment
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"createdAt\" : 1, \"authorFirstName\" : \"authorFirstName\", \"author\" : 6, \"authorImage\" : \"authorImage\", \"pk\" : 5, \"text\" : \"text\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PATCH /ads/{id}/image : Обновление картинки объявления
     *
     * @param id  (required)
     * @param image  (required)
     * @return OK (status code 200)
     *         or Forbidden (status code 403)
     *         or Unauthorized (status code 401)
     *         or Not found (status code 404)
     */
    @Operation(
        operationId = "updateImage",
        summary = "Обновление картинки объявления",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/octet-stream", array = @ArraySchema(schema = @Schema(implementation = byte[].class)))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/ads/{id}/image",
        produces = { "application/octet-stream" },
        consumes = { "multipart/form-data" }
    )
    default ResponseEntity<List<byte[]>> updateImage(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(name = "image", description = "", required = true) @RequestPart(value = "image", required = true) MultipartFile image
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(""))) {
                    String exampleString = "";
                    ApiUtil.setExampleResponse(request, "", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
