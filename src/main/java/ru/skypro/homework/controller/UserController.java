package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContext;
import ru.skypro.homework.Data.UserData;
import ru.skypro.homework.dto.UserDto;

import javax.ws.rs.core.Response;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class UserController {
    private static UserData userData = new UserData();

//    public ResponseContext createUser(final RequestContext request, final UserDto user) {
//        if (user == null) {
//            return new ResponseContext()
//                    .status(Response.Status.BAD_REQUEST)
//                    .entity("No User provided. Try again?");
//        }
//
//        userData.addUser(user);
//        return new ResponseContext()
//                .contentType(Util.getMediaType(request))
//                .entity(user);
//    }
//
//    public ResponseContext createUser(final long id, final String firstName, final String lastName, final String email,
//                                      final String city, final String phone,final String regDate,final String image, final int userStatus) {
//        final UserDto user = UserData.createUser(id,  firstName, lastName, email,city, phone,regDate, image,userStatus);
//        return createUser(request, user);
//    }
}
