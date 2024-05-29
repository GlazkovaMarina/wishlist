package ru.gb.WishList.service;
import ru.gb.WishList.domain.User;
import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserById(Long id);
    User addUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}