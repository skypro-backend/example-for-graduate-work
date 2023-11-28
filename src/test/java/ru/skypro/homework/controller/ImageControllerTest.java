package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.skypro.homework.service.ImageService;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ImageController.class})
@ExtendWith(SpringExtension.class)
class ImageControllerTest {
    @Autowired
    private ImageController imageController;

    @MockBean
    private ImageService imageService;

    /**
     * Method under test:  {@link ImageController#getImage(Long)}
     */
    @Test
    void testGetImage() throws Exception {
        when(imageService.getImage(Mockito.<Long>any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/image/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test:  {@link ImageController#getImage(Long)}
     */
    @Test
    void testGetImage2() throws Exception {
        when(imageService.getImage(Mockito.<Long>any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
