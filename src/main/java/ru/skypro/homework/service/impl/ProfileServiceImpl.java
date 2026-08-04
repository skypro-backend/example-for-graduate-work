package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.data.dto.user.UpdateUserProfileRequest;
import ru.skypro.homework.data.dto.user.UserDto;
import ru.skypro.homework.data.entity.AccountEntity;
import ru.skypro.homework.data.mapper.UserMapper;
import ru.skypro.homework.repository.AccountRepository;
import ru.skypro.homework.service.ProfileService;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {


    private final AccountRepository accountRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserProfile(String email) {
        AccountEntity accountEntity = accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find user with id: " + email));

        return userMapper.toUserDto(accountEntity);
    }

    @Override
    public UpdateUserProfileRequest updateUserProfile(String userEmail, UpdateUserProfileRequest request) {
        AccountEntity account = accountRepository.findByEmail(userEmail).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with email: " + userEmail));
        userMapper.updateProfileFromDto(request, account.getProfile());
        return request;
    }


}
