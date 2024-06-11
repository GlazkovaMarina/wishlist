package ru.gb.WishList.service;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import ru.gb.WishList.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.WishList.service.userService.UserServiceImpl;

@Service
@Schema(description = "Поставщик аутентификации. AuthenticationProvider обрабатывает запрос аутентификации, и возвращается полностью аутентифицированный объект с полными учетными данными.")
public class AuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    @Schema(description = "Эта служба сведений о пользователе имеет доступ только к имени пользователя, чтобы получить полную сущность пользователя")
    private UserServiceImpl userDetailsService;

    @Autowired
    @Schema(description = "Вид кодировки пароля")
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Operation(summary = "Получение аутентифицированного пользователя")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = (User)userDetailsService.loadUserByUsername(username);
        return checkPassword(user, password, bCryptPasswordEncoder);
    }

    @Override
    @Operation(summary = "Подтверждение токена?")
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
    @Operation(summary = "Проверка пароля")
    private Authentication checkPassword(User user, String rawPassword, PasswordEncoder encoder) {
        if (encoder.matches(rawPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

}
