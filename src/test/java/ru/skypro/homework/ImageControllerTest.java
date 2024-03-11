package ru.skypro.homework;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.controller.ImageController;
import ru.skypro.homework.service.impl.ImageServiceImpl;

import javax.transaction.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = WebTestConfiguration.class)
@AutoConfigureMockMvc
public class ImageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ImageServiceImpl imageService;
    @InjectMocks
    private ImageController imageController;

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void getImageTest() throws Exception {
        byte[] imageBytes = new byte[] {(byte) 129, (byte) 130, (byte) 131};

        when(imageService.getImage(any(Integer.class))).thenReturn(imageBytes);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/image/{id}", 1)
                        .contentType(MediaType.IMAGE_JPEG_VALUE))
                .andExpect(content().bytes(imageBytes));
    }
}
