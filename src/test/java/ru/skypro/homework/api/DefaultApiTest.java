/*
 * Api Documentation
 * Api Documentation
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package ru.skypro.homework.api;

import ru.skypro.homework.ApiException;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.CreateAds;
import java.io.File;
import ru.skypro.homework.model.FullAds;
import ru.skypro.homework.model.LoginReq;
import ru.skypro.homework.model.NewPassword;
import ru.skypro.homework.model.RegisterReq;
import ru.skypro.homework.model.ResponseWrapperAds;
import ru.skypro.homework.model.ResponseWrapperComment;
import ru.skypro.homework.model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for DefaultApi
 */
@Disabled
public class DefaultApiTest {

    private final DefaultApi api = new DefaultApi();

    /**
     * addAds
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void addAdsTest() throws ApiException {
        CreateAds properties = null;
        File image = null;
        Ads response = api.addAds(properties, image);
        // TODO: test validations
    }

    /**
     * addComments
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void addCommentsTest() throws ApiException {
        String adPk = null;
        Comment comment = null;
        Comment response = api.addComments(adPk, comment);
        // TODO: test validations
    }

    /**
     * deleteComments
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteCommentsTest() throws ApiException {
        String adPk = null;
        Integer id = null;
        api.deleteComments(adPk, id);
        // TODO: test validations
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getALLAdsTest() throws ApiException {
        ResponseWrapperAds response = api.getALLAds();
        // TODO: test validations
    }

    /**
     * getFullAd
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getAdsTest() throws ApiException {
        Integer id = null;
        FullAds response = api.getAds(id);
        // TODO: test validations
    }

    /**
     * getAdsMe
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getAdsMeUsingGETTest() throws ApiException {
        Boolean authenticated = null;
        String authorities0Authority = null;
        Object credentials = null;
        Object details = null;
        Object principal = null;
        ResponseWrapperAds response = api.getAdsMeUsingGET(authenticated, authorities0Authority, credentials, details, principal);
        // TODO: test validations
    }

    /**
     * getComments
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getCommentsTest() throws ApiException {
        String adPk = null;
        ResponseWrapperComment response = api.getComments(adPk);
        // TODO: test validations
    }

    /**
     * getComments
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getComments1Test() throws ApiException {
        String adPk = null;
        Integer id = null;
        Comment response = api.getComments1(adPk, id);
        // TODO: test validations
    }

    /**
     * getUser
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getUser1Test() throws ApiException {
        User response = api.getUser1();
        // TODO: test validations
    }

    /**
     * login
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void loginTest() throws ApiException {
        LoginReq loginReq = null;
        Object response = api.login(loginReq);
        // TODO: test validations
    }

    /**
     * register
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void registerTest() throws ApiException {
        RegisterReq registerReq = null;
        api.register(registerReq);
        // TODO: test validations
    }

    /**
     * removeAds
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void removeAdsTest() throws ApiException {
        Integer id = null;
        api.removeAds(id);
        // TODO: test validations
    }

    /**
     * setPassword
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void setPasswordTest() throws ApiException {
        NewPassword newPassword = null;
        NewPassword response = api.setPassword(newPassword);
        // TODO: test validations
    }

    /**
     * updateAds
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void updateAdsTest() throws ApiException {
        Integer id = null;
        CreateAds createAds = null;
        Ads response = api.updateAds(id, createAds);
        // TODO: test validations
    }

    /**
     * updateComments
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void updateCommentsTest() throws ApiException {
        String adPk = null;
        Integer id = null;
        Comment comment = null;
        Comment response = api.updateComments(adPk, id, comment);
        // TODO: test validations
    }

    /**
     * updateAdsImage
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void updateImageTest() throws ApiException {
        Integer id = null;
        File image = null;
        List<byte[]> response = api.updateImage(id, image);
        // TODO: test validations
    }

    /**
     * updateUser
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void updateUserTest() throws ApiException {
        User user = null;
        User response = api.updateUser(user);
        // TODO: test validations
    }

    /**
     * updateUserImage
     *
     * UpdateUserImage
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void updateUserImageTest() throws ApiException {
        File image = null;
        api.updateUserImage(image);
        // TODO: test validations
    }

}
