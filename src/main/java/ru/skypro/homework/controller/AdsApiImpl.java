package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.api.AdsApi;
import ru.skypro.homework.model.dto.*;
import ru.skypro.homework.mappers.*;

import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class AdsApiImpl implements AdsApi {
    private AdsMapper mapperAds;
    private CreateAdsMapper mapperCreateAds;

    private CommentMapper mapperComment;
    private ResponseWrapperAdsMapper mapperResponseWrapperAdsMapper;
    private FullAdsMapper mapperFullAds;
    private ResponseWrapperCommentMapper responseWrapperCommentMapper;
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return AdsApi.super.getRequest();
    }

    /**
     * POST /ads : addAds
     *
     * @param properties (required)
     * @param image      (required)
     * @return Created (status code 201)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Override
    public ResponseEntity<AdsDto> addAds(CreateAdsDto properties, MultipartFile image) {
        return addAds(properties, image);
    }

    /**
     * POST /ads/{ad_pk}/comments : addComments
     *
     * @param adPk    (required)
     * @param comment (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Override
    public ResponseEntity<CommentDto> addComments(String adPk, CommentDto comment) {
        return addComments(adPk, comment);
    }

    /**
     * DELETE /ads/{ad_pk}/comments/{id} : deleteComments
     *
     * @param adPk (required)
     * @param id   (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Override
    public ResponseEntity<Void> deleteComments(String adPk, Integer id) {
        return deleteComments(adPk, id);
    }

    /**
     * GET /ads
     *
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<ResponseWrapperAdsDto> getALLAds() {
        return getALLAds();
    }

    /**
     * GET /ads/{id} : getFullAd
     *
     * @param id (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     */
    @Override
    public ResponseEntity<FullAdsDto> getAds(Integer id) {
        return getAds(id);
    }

    /**
     * GET /ads/me : getAdsMe
     *
     * @param authenticated         (optional)
     * @param authorities0Authority (optional)
     * @param credentials           (optional)
     * @param details               (optional)
     * @param principal             (optional)
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Override
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMeUsingGET(Boolean authenticated, String authorities0Authority, Object credentials, Object details, Object principal) {
        return getAdsMeUsingGET(authenticated, authorities0Authority, credentials, details, principal);
    }
    /**
     * GET /ads/{ad_pk}/comments : getComments
     *
     * @param adPk (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     */
    @Override
    public ResponseEntity<ResponseWrapperCommentDto> getComments(String adPk) {
        return getComments(adPk);
    }

    /**
     * GET /ads/{ad_pk}/comments/{id} : getComments
     *
     * @param adPk (required)
     * @param id   (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     */
    @Override
    public ResponseEntity<CommentDto> getComments1(String adPk, Integer id) {
        return getComments1(adPk, id);
    }

    /**
     * DELETE /ads/{id} : removeAds
     *
     * @param id (required)
     * @return No Content (status code 204)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Override
    public ResponseEntity<Void> removeAds(Integer id) {
        return removeAds(id);
    }

    /**
     * PATCH /ads/{id} : updateAds
     *
     * @param id        (required)
     * @param createAds (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Override
    public ResponseEntity<AdsDto> updateAds(Integer id, CreateAdsDto createAds) {
        return updateAds(id, createAds);
    }

    /**
     * PATCH /ads/{ad_pk}/comments/{id} : updateComments
     *
     * @param adPk    (required)
     * @param id      (required)
     * @param comment (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Override
    public ResponseEntity<CommentDto> updateComments(String adPk, Integer id, CommentDto comment) {
        return updateComments(adPk, id, comment);
    }
}
