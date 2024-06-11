//package ru.gb.WishList;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.test.context.support.WithMockUser;
//import ru.gb.WishList.config.SecurityConfig;
//import ru.gb.WishList.controller.WishlistController;
//import ru.gb.WishList.repository.UserRepository;
//import ru.gb.WishList.service.userService.UserService;
//import ru.gb.WishList.entities.User;
//
//import static org.mockito.Mockito.when;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.Collections;
//import ru.gb.WishList.entities.enums.Role;
//
//@SpringBootTest
////@WebMvcTest(WishlistController.class)
////@Import(SecurityConfig.class)
////@WithMockUser(authorities = { "ADMIN", "USER" })
//public class WithMockUserTests {
//
//    @Autowired
//    private UserService userService;
//
//    @MockBean
//    private UserRepository userRepository;
//    @Test
//    void registerUserAccount() throws Exception {
//        //Блок предусловия//
//        User user = new User();
//        user.setLastName("Ivanov");
//        user.setFirstName("Ivan");
//        user.setSurname("Ivanovich");
//        user.setUsername("test@test.ru");
//        String date = "2000-08-16";
//        LocalDate localDate = LocalDate.parse(date);
//        user.setBirthday(localDate);
//        user.setRoles(Collections.singleton(Role.USER));
//
//
////
////        mockMvc.perform(get("/customer/{id}", 1L))
////                .andExpect(status().isOk());
//    }
//}
