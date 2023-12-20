package ru.skypro.homework.service.impl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.repository.ImageEntityRepository;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
    private AdEntity adEntity;
    private UserEntity userEntity;
    private ImageEntity imageEntity;
    private final String imageDir;
    private UserEntityRepository userEntityRepository;
    private AdEntityRepository adEntityRepository;
    private ImageEntityRepository imageEntityRepository;

    public ImageServiceImpl(AdEntity adEntity, UserEntity userEntity, ImageEntity imageEntity
            , @Value("${path.to.images.folder}") String imageDir, UserEntityRepository userEntityRepository
            , AdEntityRepository adEntityRepository) {
        this.adEntity = adEntity;
        this.userEntity = userEntity;
        this.imageEntity = imageEntity;
        this.imageDir = imageDir;
        this.userEntityRepository = userEntityRepository;
        this.adEntityRepository = adEntityRepository;
    }

//    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

//    @Override
//    public void uploadAvatar(String imageInfo, MultipartFile multipartFile) throws IOException {
//
//        logger.info( "Был вызван метод uploadImage с данными - о обьекте к которому относится изоборажение" + imageInfo
//                + ". Данные изображения " + multipartFile.getOriginalFilename() + " " + multipartFile.getSize() );
//
////        AdEntity adEntity = AdEntity.findStudent(adId);
//        Path filePath = Path.of(imageDir, imageInfo + ".image");
//        Files.createDirectories(filePath.getParent());
//        Files.deleteIfExists(filePath);
//
//        try (
//                InputStream is = multipartFile.getInputStream();
//                BufferedInputStream bis = new BufferedInputStream(is, 1024);
////
//                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
//                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
//        ) {
//            bis.transferTo(bos);
//        }
//        if (adEntityRepository.findByImage_entity_path(filePath.toString()).isPresent()) {
//            ImageEntity imageEntity = (imageEntityRepository.findByFilePath(filePath.toString())).get();
//            imageEntity.setFilePath(filePath.toString());
//            imageEntity.setFileSize(multipartFile.getSize());
//            imageEntity.setMediaType(multipartFile.getContentType());
//            imageEntity.setData(multipartFile.getBytes());
//            imageEntityRepository.save(imageEntity);
//            logger.info("Метод uploadAvatar обновляет в БД изображение обьявления" + imageEntity);
//        } else if (userEntityRepository.findByImage_entity_path(filePath.toString()).isPresent()) {
//            ImageEntity imageEntity = (imageEntityRepository.findByFilePath(filePath.toString())).get();
//            imageEntity.setFilePath(filePath.toString());
//            imageEntity.setFileSize(multipartFile.getSize());
//            imageEntity.setMediaType(multipartFile.getContentType());
//            imageEntity.setData(multipartFile.getBytes());
//            imageEntityRepository.save(imageEntity);
//            logger.info("Метод uploadAvatar обновляет в БД изображение пользователя" + imageEntity);
//        } else {
//            logger.info("Метод uploadAvatar сохраняет в БД изображение пользователя или обьявления" );
//        }
//
//    }
}
