package ru.gb.WishList;


import io.swagger.v3.oas.annotations.media.Schema;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.gb.WishList.entities.User;
import ru.gb.WishList.entities.enums.Role;
import ru.gb.WishList.repository.UserRepository;
import ru.gb.WishList.service.userService.UserServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.security.crypto.password.PasswordEncoder;

// загрузка полного контекста приложения
@SpringBootTest
public class UserServiceIntegrationTest {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    // используем реальные UserServiceImpl и UserRepository

    @Schema(description = "Кодировщик пароля пользователя")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void getAllNotesIntegrationTest() {
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        userRepository.deleteAll(); // Очищаем базу данных перед каждым тестом

        User user = new User();
        user.setLastName("Ivanov");
        user.setFirstName("Ivan");
        user.setSurname("Ivanovich");
        user.setUsername("test@test.ru");
        String date = "2000-08-16";
        LocalDate localDate = LocalDate.parse(date);
        user.setBirthday(localDate);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode("password")); // кодировка пароля
        userRepository.save(user);
        List<User> users = userService.findAllUsers();
        assertTrue(users.size() > 0);
        assertEquals(user.getUsername(), users.get(0).getUsername());
    }

}