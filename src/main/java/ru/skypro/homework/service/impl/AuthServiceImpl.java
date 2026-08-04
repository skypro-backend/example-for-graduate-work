package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.data.dto.auth.RegisterRequestDto;
import ru.skypro.homework.data.entity.AccountEntity;
import ru.skypro.homework.data.entity.ProfileEntity;
import ru.skypro.homework.repository.AccountRepository;
import ru.skypro.homework.repository.ProfileRepository;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final AccountRepository accountRepository;
    private final ProfileRepository profileRepository;


    @Override
    public boolean login(String userName, String password) {
        return accountRepository.findByEmail(userName)
                .map(account -> encoder.matches(password, account.getPasswordHash()))
                .orElse(false);
    }

    @Override
    public boolean register(RegisterRequestDto register) {
        if (accountRepository.findByEmail(register.getUsername()).isPresent()) {
            return false;
        }
        ProfileEntity profileEntity = profileRepository.save(
                ProfileEntity.builder()
                        .firstName(register.getFirstName())
                        .lastName(register.getLastName())
                        .phone(register.getPhone())
                        .build()
        );

        AccountEntity account = AccountEntity.builder()
                .email(register.getUsername())
                .passwordHash(encoder.encode(register.getPassword()))
                .role("USER")
                .profile(profileEntity).build();

        accountRepository.save(account);

        return true;


    }

}
