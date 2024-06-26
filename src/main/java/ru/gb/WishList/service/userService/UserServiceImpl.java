package ru.gb.WishList.service.userService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.WishList.entities.enums.Role;
import ru.gb.WishList.entities.User;
import ru.gb.WishList.exception.UserAlreadyExistException;
import ru.gb.WishList.exception.UserWithSuchIdNotFoundException;
import ru.gb.WishList.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.extern.java.Log;

@Service
@Log
@AllArgsConstructor
@Tag(name="UserServiceImpl", description="Сервис пользователя")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Schema(description = "Репозиторий пользователя")
    private final UserRepository userRepository;
    @Schema(description = "Кодировщик пароля пользователя")
    private final PasswordEncoder passwordEncoder;


    @Operation (summary = "Зарегистрировать нового пользователя",
            description = "Регистрация нового пользователя на маркетплейсе")
    @Override
    public User register(User user){
        log.info("register()");
        return userRepository.save(createUser(user));
    }


    @Operation (summary = "Создать нового пользователя",
            description = "Создание нового пользователя по полученным данных")
    public User createUser(User user) throws UserAlreadyExistException {
        log.info("createUser()");
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername()); // проверка на уникальность нового пользователя
        if (userOptional.isPresent()){
            throw new UserAlreadyExistException("Существует аккаунт с почтой: "
                    + user.getUsername());
        }
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(user.getPassword())); // кодировка пароля
        newUser.setLastName(user.getLastName());
        newUser.setFirstName(user.getFirstName());
        newUser.setSurname(user.getSurname());
        newUser.setBirthday(user.getBirthday());
        newUser.setUsername(user.getUsername());
        newUser.setRoles(Collections.singleton(Role.USER)); // с помощью формы регистрации можно создать только обычного пользователя
        return newUser;
    }

    @Operation (summary = "Найти пользователя по электронной почте",
            description = "Поиск пользователя по электронной почте")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername()");
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Проблемы во время аутентификации!");
        User user = userRepository.findByUsername(username).orElseThrow(s);
        return user;
    }
    @Operation (summary = "Найти пользователя по электронной почте",
            description = "Поиск пользователя по электронной почте")
    @Override
    public User findUserByUsername(String username) throws UsernameNotFoundException {
        log.info("findUserByUsername()");
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()){
            throw new UsernameNotFoundException("Не найден пользователь с почтой " + username);
        }
        return userOptional.get();
    }

    @Operation (summary = "Обновить данные о пользователе",
            description = "Обновить данные о пользователе в базе данных")
    @Override
    public User updateUser(User user) {
        log.info("updateUser()");
        return userRepository.save(user);
    }

    @Operation(summary = "Найти всех пользователей",
            description = "Вывод всех пользователей, зарегестрированных на маркетплейсе")
    @Override
    public List<User> findAllUsers(){
        log.info("findAllUsers()");
        return userRepository.findAll();
    }

    @Operation(summary = "Найти пользователя по id",
            description = "Поиск пользователя по идентификатору")
    @Override
    public User findUserById(Long userId){
        log.info("findUserById()");
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UserWithSuchIdNotFoundException("Не найден подарок с таким идентификатором " + userId);
        }
        return userOptional.get();
    }

    @Operation (summary = "Удалить пользователя",
            description = "Удалить пользователя из базы данных")
    @Override
    public void deleteUser(Long id) {
        log.info("deleteUser()");
        User user = findUserById(id);
        if (user != null){
            userRepository.deleteById(id);
        }
    }

}