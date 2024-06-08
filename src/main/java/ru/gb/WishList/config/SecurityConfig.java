package ru.gb.WishList.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


// класс конфигурации Spring Security
@Configuration
@EnableWebSecurity
// включили поддержку интеграции Spring Security и MVC
public class SecurityConfig{

    // Кодировщик пароля. Используем BCryptPasswordEncoder
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
        Данный бин устанавливает настройки для доступа к различным ресурсам сервиса
     */
    // 3.2. Конфигурация для авторизации запросов Мы начнем с выполнения необходимых настроек для авторизации запросов. Здесь мы разрешаем анонимный доступ к /login, чтобы пользователи могли пройти аутентификацию. Мы ограничим /admin ролями ADMIN и обеспечим безопасность всего остального:
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/images/**", "/", "/index","/login**","/registration","/personal_office").permitAll()
                        .requestMatchers("/card_item/**","/card_present/**","/correct_info/**", "/edit_present/**", "/goods/**", "goods_admin/**", "/new_present/**","/personal_office/**", "/personal_office", "/wishlist/**").hasRole("USER")
                        .requestMatchers("/card_item_admin/**", "/edit_item/**", "/new_item/**", "/personal_office_admin/**").hasRole("ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin
                .loginPage("/login.html") // пользовательская страница входа в систему
                //.loginProcessingUrl("/perform_login") // URL-адрес для отправки имени пользователя и пароля
                .defaultSuccessUrl("/personal_office.html", true) // целевая страница после успешного входа в систему
                        // Если для атрибута Always-use-default-target установлено значение true,
                        // то пользователь всегда перенаправляется на эту страницу.
                        // Если для этого атрибута установлено значение false,
                        // пользователь будет перенаправлен на предыдущую страницу,
                        // которую он хотел посетить, прежде чем ему будет предложено пройти аутентификацию.
                        .failureUrl("/login.html?error=true"))
                .logout(logout->logout
                .deleteCookies("JSESSIONID"));
        return http.build();
    }

//    // Менеджер аутентификации
//    // Здесь мы настроим трех пользователей с жестко запрограммированными именем пользователя, паролем и ролью
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user1 = User.withUsername("user1")
//                .password(bCryptPasswordEncoder().encode("user1Pass"))
//                .roles("USER")
//                .build();
//        UserDetails user2 = User.withUsername("user2")
//                .password(bCryptPasswordEncoder().encode("user2Pass"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password(bCryptPasswordEncoder().encode("adminPass"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2, admin);
//    }

}
