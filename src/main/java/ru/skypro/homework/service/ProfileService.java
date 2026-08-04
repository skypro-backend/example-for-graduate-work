package ru.skypro.homework.service;

import ru.skypro.homework.data.dto.user.UpdateUserProfileRequest;
import ru.skypro.homework.data.dto.user.UserDto;

public interface ProfileService {

    UserDto getUserProfile(String email);
    UpdateUserProfileRequest updateUserProfile(String userEmail, UpdateUserProfileRequest request);
}
