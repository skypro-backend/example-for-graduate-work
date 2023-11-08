package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.dto.UserSecurityDto;
import ru.skypro.homework.Exceptions.FindNoEntityException;

@RequiredArgsConstructor
@Component
public class UserDetailsManagerImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new UserSecurityDto(repository.findByEmail(username)
                .orElseThrow(() -> new FindNoEntityException("пользователь")));
    }
}
