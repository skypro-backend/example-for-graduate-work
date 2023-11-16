package ru.skypro.homework.models;

import ru.skypro.homework.dto.UserDTO;

import javax.persistence.*;

@Entity
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String filePath;
    private  long fileSize;
    private  String mediaType;
    @Lob
    private  byte[] data;

    @OneToOne
    private UserDTO userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public UserDTO getStudent() {
        return userId;
    }

    public void setStudent(UserDTO userId) {
        this.userId = userId;
    }
}
