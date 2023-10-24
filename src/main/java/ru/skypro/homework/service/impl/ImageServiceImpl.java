package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.Adler32;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final Adler32 adler32 = new Adler32();

    private String storePath;

    private Path getPath(String filename) throws IOException {
        var path = Paths.get(storePath, filename);
        if (!path.normalize().startsWith(storePath)) {
            throw new IOException("Access denied");
        }
        return path;
    }

    private String getFileExt(String filename) {
        return filename == null ? "bin" : filename.substring(filename.lastIndexOf(".") + 1);
    }

    /**
     * запись изображения в хранилище
     */
    @Override
    public String create(MultipartFile multipartFile) throws IOException {
        adler32.reset();
        adler32.update(multipartFile.getBytes());
        var ext = getFileExt(multipartFile.getOriginalFilename());
        var filename = adler32.getValue() + "." + ext;
        var path = getPath(filename);
        if (Files.notExists(path)) {
            Files.write(path, multipartFile.getBytes());
        }
        return filename;
    }

}
