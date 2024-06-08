package ru.gb.WishList.service.userService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.gb.WishList.entities.User;
import ru.gb.WishList.exception.UserAlreadyExistException;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserById(Long id);
    User registrate(User user);
    //User updateUser(User user, Long id);
    void deleteUser(Long id);
    User updateUser(User user);
}