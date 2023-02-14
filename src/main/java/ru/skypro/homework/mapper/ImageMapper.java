package ru.skypro.homework.mapper;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public String asString(byte[] image){
        return Base64.encodeBase64URLSafeString(image);
    }

    public byte[] asByteArray(String image) {
        return Base64.decodeBase64(image);
    }
}
