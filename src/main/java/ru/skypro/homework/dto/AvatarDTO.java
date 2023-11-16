package ru.skypro.homework.dto;

import ru.skypro.homework.models.Avatar;

public class AvatarDTO {
    public Long getId(Avatar avatar) {
        return avatar.getId();
    }

    public String getFilePath(Avatar avatar) {
        return avatar.getFilePath();
    }

    public String getMediaType(Avatar avatar) {
        return avatar.getMediaType();
    }

    public byte[] getData(Avatar avatar) {
        return avatar.getData();
    }

    public void setFilePath(Avatar avatar, String pathFile){
        avatar.setFilePath(pathFile);
    }

    public void setMediaType(Avatar avatar, String mediaType){
        avatar.setFilePath(mediaType);
    }

    public void setData(Avatar avatar, byte[] data){
        avatar.setData(data);
    }
}
