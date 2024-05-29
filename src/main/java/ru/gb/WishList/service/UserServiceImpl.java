package ru.gb.WishList.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.WishList.domain.User;
import ru.gb.WishList.repository.UserRepository;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User addUser(User user){
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user;
        } else {
            // TODO: переделать возврат, чтоб не было ошибок
            return null;
        }
    }

    @Override
    public User updateUser(Long id, User user) {
        // мы должны сначала проверить, существует ли User с данным ID
        User existingUser = findUserById(id);
        // обновляем поля существующего User
        existingUser.setLastName(user.getLastName());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setSurname(user.getSurname());
        existingUser.setNumber(user.getNumber());
        existingUser.setEmail(user.getEmail());
        existingUser.setBirthday(user.getBirthday());
        // сохраняем и возвращаем обновленного User
        return userRepository.save(existingUser);
    }
    @Override
    public void deleteUser(Long id) {
        // проверяем, существует ли User с данным ID
        User user = findUserById(id);
        if (user != null){
            userRepository.deleteById(id);
        }
    }

}