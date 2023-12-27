package ru.skypro.homework.service.helper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class AuthenticationCheck {

    public void accessCheck(CustomUserDetails userDetails, UserEntity userEntity) {

        if (!userDetails.getUsername().equals(userEntity.getUsername()) && !(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))))  {
            throw new AuthenticationCredentialsNotFoundException("Denial of access. An unauthorized user (not an ADMIN or a author USER)");
        }
    }

    }
