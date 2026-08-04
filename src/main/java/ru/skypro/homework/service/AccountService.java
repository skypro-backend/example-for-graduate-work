package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.data.dto.user.SetPasswordRequest;
import ru.skypro.homework.data.entity.AccountEntity;
import ru.skypro.homework.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void setPassword(String email, SetPasswordRequest request) {
        AccountEntity account = accountRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with email: " + email)
        );

        boolean canChange = passwordEncoder.matches(request.getCurrentPassword(), account.getPasswordHash());
        if (!canChange) {
            throw new BadCredentialsException("Current password is not correct");
        }
        account.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
    }

}
