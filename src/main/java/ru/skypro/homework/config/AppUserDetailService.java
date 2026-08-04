package ru.skypro.homework.config;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.data.entity.AccountEntity;
import ru.skypro.homework.repository.AccountRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(@Nonnull String email) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с email %s не найден".formatted(email)));
        String roleName = account.getRole().startsWith("ROLE_")
                ? account.getRole()
                : "ROLE_" + account.getRole();

        return new User(
                account.getEmail(),
                account.getPasswordHash(),
                Collections.singletonList(
                        new SimpleGrantedAuthority(roleName)
                )
        );
    }
}
