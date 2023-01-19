package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.api.AdsApi;
import ru.skypro.homework.model.*;

import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class AdsApiImpl implements AdsApi {
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
    public ResponseEntity<Ads> addAds(CreateAds properties, MultipartFile image) {
        return AdsApi.super.addAds(properties, image);
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
    public ResponseEntity<Comment> addComments(String adPk, Comment comment) {
        return AdsApi.super.addComments(adPk, comment);
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
        return AdsApi.super.deleteComments(adPk, id);
    }

    /**
     * GET /ads
     *
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<ResponseWrapperAds> getALLAds() {
        return AdsApi.super.getALLAds();
    }

    /**
     * GET /ads/{id} : getFullAd
     *
     * @param id (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     */
    @Override
    public ResponseEntity<FullAds> getAds(Integer id) {
        return AdsApi.super.getAds(id);
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
    public ResponseEntity<ResponseWrapperAds> getAdsMeUsingGET(Boolean authenticated, String authorities0Authority, Object credentials, Object details, Object principal) {
        return AdsApi.super.getAdsMeUsingGET(authenticated, authorities0Authority, credentials, details, principal);
    }

    /**
     * GET /ads/{ad_pk}/comments : getComments
     *
     * @param adPk (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     */
    @Override
    public ResponseEntity<ResponseWrapperComment> getComments(String adPk) {
        return AdsApi.super.getComments(adPk);
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
    public ResponseEntity<Comment> getComments1(String adPk, Integer id) {
        return AdsApi.super.getComments1(adPk, id);
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
        return AdsApi.super.removeAds(id);
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
    public ResponseEntity<Ads> updateAds(Integer id, CreateAds createAds) {
        return AdsApi.super.updateAds(id, createAds);
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
    public ResponseEntity<Comment> updateComments(String adPk, Integer id, Comment comment) {
        return AdsApi.super.updateComments(adPk, id, comment);
    }
}
