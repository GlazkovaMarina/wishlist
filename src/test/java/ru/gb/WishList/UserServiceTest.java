package ru.gb.WishList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.gb.WishList.entities.enums.Role;
import ru.gb.WishList.service.userService.UserService;
import ru.gb.WishList.repository.UserRepository;
import ru.gb.WishList.entities.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.gb.WishList.service.userService.UserServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import io.swagger.v3.oas.annotations.media.Schema;

public class UserServiceTest {
    @Schema(description="Инициализация сервиса")
    @InjectMocks
    private UserServiceImpl userService;
    @Schema(description="Создание Mock")
    @Mock
    private UserRepository userRepository;

    @Test
    public void getAllNotesTest() {
        MockitoAnnotations.initMocks(this);
        User user = new User();
        user.setLastName("Ivanov");
        user.setFirstName("Ivan");
        user.setSurname("Ivanovich");
        user.setUsername("test@test.ru");
        String date = "2000-08-16";
        LocalDate localDate = LocalDate.parse(date);
        user.setBirthday(localDate);
        user.setRoles(Collections.singleton(Role.USER));
        List<User> expectedUsers = Collections.singletonList(user);
        // настройка того, что будет возвращать
        // наш мок-репозиторий при вызове метода findAll()
        when(userRepository.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userService.findAllUsers();
        //  сравниваем ожидаемый результат и результат, который вернул наш сервис
        assertEquals(expectedUsers, actualUsers);
    }

}