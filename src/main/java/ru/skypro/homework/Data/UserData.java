package ru.skypro.homework.Data;

import ru.skypro.homework.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static List<UserDto> users = new ArrayList<>();

    static {
        users.add(createUser(1,  "first name 1", "last name 1","email1@test.com",
                "city1","123-456-7890","Date1","Image1", 1));
        users.add(createUser(2,  "first name 2", "last name 2",
                "email2@test.com","city2", "123-456-7890","Date2","Image2", 2));
        users.add(createUser(3, "first name 3", "last name 3",
                "email3@test.com","city3", "123-456-7890", "Date3","Image3",3));
        users.add(createUser(4,  "first name 4", "last name 4",
                "email4@test.com", "city4","123-456-7890", "Date4","Image4",1));
        users.add(createUser(5,  "first name 5", "last name 5",
                "email5@test.com","city5", "123-456-7890","Date5", "Image5",2));
        users.add(createUser(6,  "first name 6", "last name 6",
                "email6@test.com","city6", "123-456-7890", "Date6","Image6",3));
        users.add(createUser(7,  "first name 7", "last name 7",
                "email7@test.com","city7", "123-456-7890","Date7", "Image7",1));
        users.add(createUser(8, "first name 8", "last name 8",
                "email8@test.com","city8", "123-456-7890","Date8","Image8", 2));
        users.add(createUser(9,  "first name 9", "last name 9",
                "email9@test.com", "city9","123-456-7890", "Date9","Image9",3));
        users.add(createUser(10,  "first name 10", "last name 10",
                "email10@test.com","city10", "123-456-7890", "Date10","Image10",1));
        users.add(createUser(11,  "first name ?10", "last name ?10",
                "email101@test.com", "city11","123-456-7890", "Date11","Image11",1));

    }

    public UserDto findUserByName(final String username) {
        for (final UserDto user : users) {
            if (user.getLastName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(final UserDto user) {
        if (users.size() > 0) {
            for (int i = users.size() - 1; i >= 0; i--) {
                if (users.get(i).getLastName().equals(user.getLastName())) {
                    users.remove(i);
                }
            }
        }
        users.add(user);
    }

    public void deleteUser(final String username) {
        users.removeIf(user -> user.getLastName().equals(username));
    }

    public static UserDto createUser(final long id, final String firstName, final String lastName, final String email,
                                     final String city, final String phone,final String regDate,final String image, final int userStatus) {
        final UserDto user = new UserDto();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCity(city);
        user.setPhone(phone);
        user.setRegDate(regDate);
        user.setImage(image);
        user.setUserStatus(userStatus);
        return user;
    }
}
