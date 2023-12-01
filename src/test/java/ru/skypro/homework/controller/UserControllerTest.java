package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.ImageServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.io.InputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test:  {@link UserController#updateUser(UpdateUserDTO)}
     */
    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(Mockito.<UpdateUserDTO>any())).thenReturn(new UserDTO());

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.firstName("Jane");
        updateUserDTO.lastName("Doe");
        updateUserDTO.phone("6625550144");
        updateUserDTO.setFirstName("Jane");
        updateUserDTO.setLastName("Doe");
        updateUserDTO.setPhone("6625550144");
        String content = (new ObjectMapper()).writeValueAsString(updateUserDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/users/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"email\":null,\"firstName\":null,\"lastName\":null,\"phone\":null,\"role\":null,\"image\":null}"));
    }

    /**
     * Method under test:  {@link UserController#updateUser(UpdateUserDTO)}
     */
    @Test
    void testUpdateUser2() throws Exception {
        when(userService.updateUser(Mockito.<UpdateUserDTO>any())).thenReturn(null);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.firstName("Jane");
        updateUserDTO.lastName("Doe");
        updateUserDTO.phone("6625550144");
        updateUserDTO.setFirstName("Jane");
        updateUserDTO.setLastName("Doe");
        updateUserDTO.setPhone("6625550144");
        String content = (new ObjectMapper()).writeValueAsString(updateUserDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/users/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(status().isNotFound());
    }

    /**
     * Method under test:  {@link UserController#getUser()}
     */
    @Test
    void testGetUser() throws Exception {
        when(userService.getCurrentUser()).thenReturn(new UserDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/me");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"email\":null,\"firstName\":null,\"lastName\":null,\"phone\":null,\"role\":null,\"image\":null}"));
    }

    /**
     * Method under test:  {@link UserController#getUser()}
     */
    @Test
    void testGetUser2() throws Exception {
        when(userService.getCurrentUser()).thenReturn(new UserDTO());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#loadUserImage(MultipartFile)}
     */
    @Test
    void testLoadUserImage() throws Exception {
        MockHttpServletRequestBuilder patchResult = MockMvcRequestBuilders.patch("/users/me/image");
        MockHttpServletRequestBuilder requestBuilder = patchResult.param("image",
                String.valueOf(new MockMultipartFile("Name", (InputStream) null)));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(status().is(415));
    }
    /**
     * Method under test: {@link UserController#setUserPassword(NewPasswordDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetUserPassword() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class ru.skypro.homework.dto.NewPasswordDTO]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `ru.skypro.homework.dto.NewPasswordDTO` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:555)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:623)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `ru.skypro.homework.dto.NewPasswordDTO` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1904)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:400)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1349)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1415)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4674)
        //       at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3682)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:555)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:623)
        //   See https://diff.blue/R013 to resolve this issue.

        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        UserRepository userRepository = mock(UserRepository.class);
        UserController userController = new UserController(
                new UserServiceImpl(encoder, userRepository, new ImageServiceImpl(mock(ImageRepository.class))));
        userController.setUserPassword(new NewPasswordDTO("iloveyou", "iloveyou"));
    }
}
