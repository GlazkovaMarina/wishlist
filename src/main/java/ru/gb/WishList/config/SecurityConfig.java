package ru.gb.WishList.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import ru.gb.WishList.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import ru.gb.WishList.entities.enums.Role;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;


// класс конфигурации Spring Security
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
// включили поддержку интеграции Spring Security и MVC
public class SecurityConfig{

    // Кодировщик пароля. Используем BCryptPasswordEncoder
    @Bean
    public PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
        Данный бин устанавливает настройки для доступа к различным ресурсам сервиса
     */
    // 3.2. Конфигурация для авторизации запросов Мы начнем с выполнения необходимых настроек для авторизации запросов. Здесь мы разрешаем анонимный доступ к /login, чтобы пользователи могли пройти аутентификацию. Мы ограничим /admin ролями ADMIN и обеспечим безопасность всего остального:
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/images/**", "/", "/index","/login**","/registration").permitAll()
                        .requestMatchers("/card_item/**","/card_present/**","/correct_info/**", "/edit_present/**", "/goods/**", "/goods_admin/**", "/new_present/**","/personal_office/**", "/personal_office", "/wishlist/**").authenticated()
                        .requestMatchers("/user","/card_item_admin/**", "/edit_item/**", "/new_item/**", "/personal_office_admin/**").authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin
                .loginPage("/login") // пользовательская страница входа в систему
                //.loginProcessingUrl("/perform_login") // URL-адрес для отправки имени пользователя и пароля
                .defaultSuccessUrl("/personal_office", true)) // целевая страница после успешного входа в систему
                        // Если для атрибута Always-use-default-target установлено значение true,
                        // то пользователь всегда перенаправляется на эту страницу.
                        // Если для этого атрибута установлено значение false,
                        // пользователь будет перенаправлен на предыдущую страницу,
                        // которую он хотел посетить, прежде чем ему будет предложено пройти аутентификацию.
                .logout(logout->logout
                .deleteCookies("JSESSIONID"));
        return http.build();
    }

//    // Менеджер аутентификации
//    // Здесь мы настроим трех пользователей с жестко запрограммированными именем пользователя, паролем и ролью
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        User user = new User();
//        user.setLastName("Ivanov");
//        user.setFirstName("Ivan");
//        user.setSurname("Ivanovich");
//        user.setUsername("user");
//        String date = "2016-08-16";
//        LocalDate localDate = LocalDate.parse(date);
//        user.setBirthday(localDate);
//        user.setRoles(Collections.singleton(Role.USER));
//        user.setPassword(PasswordEncoder().encode("user"));
//        UserDetails user1 = user;
////                User.withUsername("user1")
////                .password(PasswordEncoder().encode("user1Pass"))
////                .roles("USER")
////                .build();
////        UserDetails user2 = User.withUsername("user2")
////                .password(PasswordEncoder().encode("user2Pass"))
////                .roles("USER")
////                .build();
////        UserDetails admin = User.withUsername("admin")
////                .password(PasswordEncoder().encode("adminPass"))
////                .roles("ADMIN")
////                .build();
//        return new InMemoryUserDetailsManager(user1);
//    }

}
