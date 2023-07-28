//package ru.skypro.homework.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Service
//public class FileUploadService {
//
//    private static final String UPLOAD_DIR = "uploads/";
//
//    public String uploadFile(MultipartFile file) {
//        try {
//            String fileName = file.getOriginalFilename();
//            Path filePath = Paths.get(UPLOAD_DIR + fileName);
//            file.transferTo(new File(String.valueOf(filePath)));
//            return filePath.toString();
//
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to store file", e);
//        }
//    }
//}
