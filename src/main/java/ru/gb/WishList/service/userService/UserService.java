package ru.gb.WishList.service.userService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.gb.WishList.entities.User;
import ru.gb.WishList.exception.UserAlreadyExistException;

import java.util.List;

@Schema(description = "Сервис пользователя")
public interface UserService {
    @Operation(summary = "Найти всех пользователей",
            description = "Вывод всех пользователей, зарегестрированных на маркетплейсе")
    List<User> findAllUsers();

    @Operation (summary = "Найти пользователя по id",
            description = "Поиск пользователя по идентификатору")
    User findUserById(Long id);
    @Operation (summary = "Зарегистрировать нового пользователя",
            description = "Регистрация нового пользователя на маркетплейсе")
    User register(User user);
    @Operation (summary = "Удалить пользователя",
            description = "Удалить пользователя из базы данных")
    void deleteUser(Long id);
    @Operation (summary = "Обновить данные о пользователе",
            description = "Обновить данные о пользователе в базе данных")
    User updateUser(User user);
    @Operation (summary = "Найти пользователя по электронной почте",
            description = "Поиск пользователя по электронной почте")
    User findUserByUsername(String username) throws UsernameNotFoundException;
}