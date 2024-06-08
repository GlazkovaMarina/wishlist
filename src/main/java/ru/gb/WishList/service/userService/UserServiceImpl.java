package ru.gb.WishList.service.userService;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.WishList.entities.enums.Role;
import ru.gb.WishList.entities.User;
import ru.gb.WishList.exception.UserAlreadyExistException;
//import ru.gb.WishList.repository.RoleRepository; // при реализации role через class
import ru.gb.WishList.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;

@Service
@Log
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
//    private final RoleRepository roleRepository; // при реализации role через class
    private final PasswordEncoder passwordEncoder;

    // поиск пользователя в базе данных
    @Override
    public User registrate(User user){
        return userRepository.save(createUser(user));
    }

    //поиск пользователя в базе данных и сохранение в БД, если пользователь не найден

    public User createUser(User user) throws UserAlreadyExistException {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()){
            throw new UserAlreadyExistException("Существует аккаунт с почтой: "
                    + user.getUsername());
        }
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setLastName(user.getLastName());
        newUser.setFirstName(user.getFirstName());
        newUser.setSurname(user.getSurname());
        newUser.setBirthday(user.getBirthday());
        newUser.setUsername(user.getUsername());
        newUser.setRoles(Collections.singleton(Role.USER));
        log.severe(newUser.toString()); // для отладки
        return newUser;
    }

    // поиск пользователя в базе данных
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow();
    }

    @Override
    public User updateUser(User user) {
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
    public void deleteUser(Long id) {
        // проверяем, существует ли User с данным ID
        User user = findUserById(id);
        if (user != null){
            userRepository.deleteById(id);
        }
    }

}