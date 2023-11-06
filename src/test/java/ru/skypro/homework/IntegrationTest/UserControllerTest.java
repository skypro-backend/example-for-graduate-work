//package ru.skypro.homework.IntegrationTest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.skypro.homework.HomeworkApplication;
//import ru.skypro.homework.repository.UserRepo;
//
//@SpringBootTest(classes = HomeworkApplication.class)
//@AutoConfigureMockMvc(addFilters = false)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class UserControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    public UserRepo userRepo;
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @BeforeEach
//    private void cleanData() {
//        userRepo.deleteAll();
//    }
//
//    @Test
//
//}
