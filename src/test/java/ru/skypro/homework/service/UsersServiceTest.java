package ru.skypro.homework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Avatar;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AvatarRepository;
import ru.skypro.homework.repository.UsersRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    UsersRepository usersRepository;
    @Mock
    AvatarRepository avatarRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    UsersService usersService;

    @Test
    void updateUser() {
        UserDTO dto = new UserDTO();
        assertThrows(NotFoundException.class, () -> usersService.updateUser(dto));

    }

    @Test
    void setAvatar() {
        MultipartFile image = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        when(usersRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        when(avatarRepository.save(any(Avatar.class))).thenReturn(new  Avatar());

        assertDoesNotThrow(() -> usersService.setAvatar(1L, image));
    }

    @Test
    void getAuthorisedUser() {
        assertThrows(NotFoundException.class, () -> usersService.getAuthorisedUser("TEST"));
        when(usersRepository.findByEmail("TEST")).thenReturn(Optional.of(new User()));
        assertDoesNotThrow(() -> usersService.getAuthorisedUser("TEST"));
    }

    @Test
    void setPassword() {
        NewPasswordDTO dto = new NewPasswordDTO();
        assertThrows(NotFoundException.class, () -> usersService.setPassword(1L, dto));
    }
}