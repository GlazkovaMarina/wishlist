package ru.gb.WishList.service.userService;
import ru.gb.WishList.domain.User;
import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserById(Long id);
    User saveUser(User user);
    //User updateUser(User user, Long id);
    void deleteUser(Long id);
}