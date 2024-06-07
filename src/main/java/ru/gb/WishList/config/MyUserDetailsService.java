package ru.gb.WishList.config;

import ru.gb.WishList.domain.User;
import ru.gb.WishList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    // может через сервис???/
    @Autowired
    private UserRepository userRepository;

    // переписать исключение и метод? на loadUserByEmail
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        return user.map(MyUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException(email+"Нет такого пользователя в базе данных"));
    }
}