package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.pojo.Avatar;

import ru.skypro.homework.repository.AvatarRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AvatarServiceImpl implements  AvatarService{

    private final AvatarRepository avatarRepository;



    public AvatarServiceImpl(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }


    @Override
    public String saveFile(byte[] data, String fileName) throws IOException {
        String UPLOAD_DIRECTORY = "C:/Media";
        String filePath = UPLOAD_DIRECTORY + "/" + fileName;
        Path path = Paths.get(filePath);
        Files.write(path, data);
        return filePath;
    }

    @Override
    public Avatar uploadAvatar(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] data = file.getBytes();
            String fileName = file.getOriginalFilename();

            // Сохранение файла на локальный диск
            String filePath = saveFile(data, fileName);

            // Сохранение пути к файлу в базе данных
            Avatar avatar = new Avatar();
            avatar.setFilePath(filePath);
            avatar.setFileSize(file.getSize());
            avatar.setMediaType(file.getContentType());
            avatar.setData(file.getBytes());

            avatarRepository.save(avatar);

        } else {
            throw new IllegalArgumentException("Uploaded file is empty");
        }
        return null;
    }
}
