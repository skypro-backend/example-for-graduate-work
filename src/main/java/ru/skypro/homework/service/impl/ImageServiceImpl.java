package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.util.UserAuthentication;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serial;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ImageServiceImpl implements ImageService {
    private final AdsRepository adsRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final UserAuthentication userAuthentication;

    @Override
    public void updateUserImage(MultipartFile image) throws IOException {
        User user = userAuthentication.getCurrentUserName();


    }

    @Override
    public byte[] updateAdImage(Integer id, MultipartFile image) {
        return new byte[0];
    }
}
