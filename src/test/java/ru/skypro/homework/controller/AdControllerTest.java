package ru.skypro.homework.controller;


import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.RoleDTO;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AdMapper adMapper;
    @MockBean
    private AdRepository adRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private ImageRepository imageRepository;
    @SpyBean
    private AdDTO adDTO;
    private Ad ad;
    private CreateOrUpdateComment createOrUpdateComment;
    @InjectMocks
    AdController adController;
    private Authentication auth;
    @SpyBean
    private User user;
    @SpyBean
    private Image image;
    @SpyBean
    AdService adService;


    final int pk = 123;
    final User author = new User();
    final int price = 10000;
    final String title = "test";
    final String description = "description";
    Ad ads = prepareAd();
    JSONObject adObject = prepareAdObject();


    @Test
    @WithMockUser("test")
    public void addAdsTest() throws Exception {
        when(adRepository.save(any(Ad.class))).thenReturn(ads);

        JSONObject createOrUpdateAd = new JSONObject();
        createOrUpdateAd.put("title", "test");
        createOrUpdateAd.put("price", 10000);
        createOrUpdateAd.put("description", "description");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image", new byte[]{1,2,3,4});
        var mockPart = new MockPart("properties", createOrUpdateAd.toString().getBytes(StandardCharsets.UTF_8));
        mockPart.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(multipart("/ads")
                        .file(mockMultipartFile)
                        .part(mockPart)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pk").value(pk))
                .andExpect(jsonPath("$.author").value(author))
                .andExpect(jsonPath("$.image").value(image))
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.description").value(description));
    }

    @Test
    void allTest() throws Exception {
        mockMvc.perform(get("/ad"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.count").isNumber())
                .andExpect(jsonPath("$.results").isArray());
    }

    @Test
    void findAdByIdTest()throws Exception {

    }


    private JSONObject prepareAdObject() {
        JSONObject adObject = new JSONObject();
        adObject.put("pk", pk);
        adObject.put("user", author);
        adObject.put("image", image);
        adObject.put("price", price);
        adObject.put("title", title);
        adObject.put("description", description);

        return adObject;
    }

    private Ad prepareAd() {
        Ad ads = new Ad();
        ads.setPk(pk);
        ads.setAuthor(author);
        ads.setImage(image);
        ads.setPrice(price);
        ads.setTitle(title);
        ads.setDescription(description);

        return ads;
    }
}
