package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.api.UsersApi;
import ru.skypro.homework.model.dto.NewPasswordDto;
import ru.skypro.homework.model.entity.ProfileUser;

import java.util.Optional;



@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class UsersApiImpl implements UsersApi {
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UsersApi.super.getRequest();
    }

    /**
     * GET /users/me : getUser
     *
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Override
    public ResponseEntity<ProfileUser> getUser1() {
        return UsersApi.super.getUser1();
    }

    /**
     * POST /users/set_password : setPassword
     *
     * @param newPassword (required)
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Override
    public ResponseEntity<NewPasswordDto> setPassword(NewPasswordDto newPassword) {
        return UsersApi.super.setPassword(newPassword);
    }

    /**
     * PATCH /users/me : updateUser
     *
     * @param profileUser (required)
     * @return OK (status code 200)
     * or No Content (status code 204)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Override
    public ResponseEntity<ProfileUser> updateUser(ProfileUser profileUser) {
        return UsersApi.super.updateUser(profileUser);
    }

    /**
     * PATCH /users/me/image : updateUserImage
     * UpdateUserImage
     *
     * @param image (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     */
    @Override
    public ResponseEntity<Void> updateUserImage(MultipartFile image) {
        return UsersApi.super.updateUserImage(image);
    }
}
