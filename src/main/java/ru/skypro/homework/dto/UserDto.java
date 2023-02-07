package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class UserDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String phone;
    private String regDate;
    private String image;
    private int userStatus;

    @XmlElement(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlElement(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "username")
    public String getRegDate() {        return regDate;    }

    public void setRegDate(String regDate) {        this.regDate = regDate;    }

    @XmlElement(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "password")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @XmlElement(name = "image")
    public String getImage() {        return image;    }

    public void setImage(String image) {        this.image = image;    }

    @XmlElement(name = "userStatus")
    @Schema(description = "User Status", allowableValues = "1-registered,2-active,3-closed")
    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
}
