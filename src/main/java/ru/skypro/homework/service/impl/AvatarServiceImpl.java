package ru.skypro.homework.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Avatar;
import ru.skypro.homework.repository.AvatarRepository;
import ru.skypro.homework.service.AvatarService;

import java.io.IOException;

@Service
@RequiredArgsConstructor

public class AvatarServiceImpl implements AvatarService {
    private final AvatarRepository repository;

    @Override
    public Avatar uploadAvatar(MultipartFile imageFile) {
        Avatar avatar = new Avatar();
        try{
            avatar.setData(imageFile.getBytes());
            avatar.setFileSize(imageFile.getSize());
            avatar.setMediaType(imageFile.getContentType());
        }catch (IOException e){
            e.printStackTrace();
        }
        avatar = repository.save(avatar);
        return avatar;
    }
    @Override
    public void removeAvatar(Avatar avatar) {
        repository.delete(avatar);

    }
    @Override
    public Avatar getAvatar(Long id) {
        return repository.findById(id).orElse(new Avatar());
    }
}
