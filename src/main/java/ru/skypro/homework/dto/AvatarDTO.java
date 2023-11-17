package ru.skypro.homework.dto;

import ru.skypro.homework.model.AvatarEntity;

public class AvatarDTO {
    public Integer getId(AvatarEntity avatar) {
        return avatar.getId();
    }

    public String getFilePath(AvatarEntity avatar) {
        return avatar.getFilePath();
    }

    public String getMediaType(AvatarEntity avatar) {
        return avatar.getMediaType();
    }

    public byte[] getData(AvatarEntity avatar) {
        return avatar.getData();
    }

    public void setFilePath(AvatarEntity avatar, String pathFile){
        avatar.setFilePath(pathFile);
    }

    public void setMediaType(AvatarEntity avatar, String mediaType){
        avatar.setFilePath(mediaType);
    }

    public void setData(AvatarEntity avatar, byte[] data){
        avatar.setData(data);
    }
}
