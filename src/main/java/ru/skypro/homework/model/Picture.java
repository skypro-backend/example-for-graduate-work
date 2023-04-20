package ru.skypro.homework.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

//@Data
public interface Picture {
    String id = null;
    byte[] data = new byte[0];
    String getId();
    void setId(String id);
    byte[] getData();
    void setData(byte[] data);
}
