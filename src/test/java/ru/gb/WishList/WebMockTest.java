//package ru.gb.WishList;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.time.LocalDate;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import ru.gb.WishList.controller.WishlistController;
//import ru.gb.WishList.entities.User;
//import ru.gb.WishList.repository.UserRepository;
//import ru.gb.WishList.service.userService.UserService;
//import java.util.Collections;
//import ru.gb.WishList.entities.enums.Role;
//import java.util.List;
////@SpringBootTest
//@WebMvcTest(WishlistController.class)
//class WebMockTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private UserRepository userRepository;
//
////    @BeforeAll
////    static public void testUsers(){
////        User user1 = new User();
////        user1.setLastName("Ivanov");
////        user1.setFirstName("Ivan");
////        user1.setSurname("Ivanovich");
////        user1.setUsername("test1@test.ru");
////        String date = "2000-08-16";
////        LocalDate localDate = LocalDate.parse(date);
////        user1.setBirthday(localDate);
////        user1.setRoles(Collections.singleton(Role.USER));
//
////        User user2 = new User();
////        user2.setLastName("Petrov");
////        user2.setFirstName("Petr");
////        user2.setSurname("Petrovich");
////        user2.setUsername("test2@test.ru");
////        user2.setBirthday(localDate);
////        user2.setRoles(Collections.singleton(Role.ADMIN));
////        // TODO: дореализовать aop
//////        userRepository.save(user1);
//////        userRepository.save(user2);
////    }
//
//
////    @Test
////    public void whenGetRequestToUsersEndPointWithIdPathVariable_thenCorrectResponse() throws Exception {
////        User user1 = new User();
////        user1.setLastName("Ivanov");
////        user1.setFirstName("Ivan");
////        user1.setSurname("Ivanovich");
////        user1.setUsername("test1@test.ru");
////        String date = "2000-08-16";
////        LocalDate localDate = LocalDate.parse(date);
////        user1.setBirthday(localDate);
////        user1.setRoles(Collections.singleton(Role.USER));
////        userRepository.save(user1);
////        mockMvc.perform(MockMvcRequestBuilders.get("/correct_info/{user_id}", "1")
////                        .contentType(MediaType.APPLICATION_JSON_UTF8))
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
////    }
////}
//
//
////    @Test
////    public void shouldReturnUser() throws Exception {
////        User user = new User();
////        user.setId(1L);
////        user.setLastName("Ivanov");
////        user.setFirstName("Ivan");
////        user.setSurname("Ivanovich");
////        user.setUsername("test@test.ru");
////        String date = "2000-08-16";
////        LocalDate localDate = LocalDate.parse(date);
////        user.setBirthday(localDate);
////        user.setRoles(Collections.singleton(Role.USER));
////
////        when(service.findUserByUsername("test@test.ru")).thenReturn(
////                user);
////
////        this.mockMvc.perform(get("/correct_info/1"))
////                .andExpect(status().isOk()).
////                andExpect(content().string(containsString("correct_info")));
//////                .andExpect(jsonPath("$[0].id", Matchers.is("42")))
//////                .andExpect(jsonPath("$[0].cartItems.length()", Matchers.is(1)))
//////                .andExpect(jsonPath("$[0].cartItems[0].item.name", Matchers.is("MacBook")))
//////                .andExpect(jsonPath("$[0].cartItems[0].quantity", Matchers.is(2)));
////    }
////}
//
////    @Test
////    public void test() throws Exception{
////        this.mockMvc
////                .perform(get("/index"))
////                        .andDo(print())
////                .andExpect(status().isOk())
////                .andExpect(content().string(containsString("Добавь товары в wishlist и поделись им со своими контактами")));
////    }
//
////    @MockBean
////    private UserService userService;
//
//
//
////    @Test
////    void greetingShouldReturnMessageFromService() throws Exception {
////        User user = new User();
////        user.setLastName("Ivanov");
////        user.setFirstName("Ivan");
////        user.setSurname("Ivanovich");
////        user.setUsername("test@test.ru");
////        String date = "2000-08-16";
////        LocalDate localDate = LocalDate.parse(date);
////        user.setBirthday(localDate);
////        user.setRoles(Collections.singleton(Role.USER));
////        when(userService.registrate(user).thenReturn(userService.findUserByUsername("test@test.ru")));
////        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
////                .andExpect(content().string(containsString("Hello, Mock")));
////    }
////}