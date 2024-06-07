package ru.gb.WishList.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import ru.gb.WishList.domain.Role;
import ru.gb.WishList.domain.User;
import ru.gb.WishList.service.userService.UserService;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private UserService userService;
    @Enumerated(EnumType.STRING)
    private Role roleUser = Role.USER;


    @Enumerated(EnumType.STRING)
    private Role roleAdmin = Role.ADMIN;

    @Bean //возвращаем кастомный MyUserDetailsService
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/css/**", "/", "/index", "/entry", "/registration").permitAll() // вход без авторизации
                        .requestMatchers("/card_item/**","/card_present/**","/correct_info/**", "/edit_present/**", "/goods/**", "goods_admin/**", "/new_present/**","/personal_office/**", "/personal_office", "/wishlist/**").hasRole(roleUser.toString()) // Позволяем обычным пользователям доступ к перечисленным эндпоинтам
                        .requestMatchers("/card_item_admin/**", "/edit_item/**", "/new_item/**", "/personal_office_admin/**").hasRole(roleAdmin.toString()) // Позволяем администратору доступ к перечисленным эндпоинтам
                        .anyRequest().authenticated() // Все остальные эндпоинты требуют аутентификации
                )
                .formLogin(form -> form
                        .loginPage("/entry")
                        .defaultSuccessUrl("/index", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/"));
        return http.build();
    }
    @Bean
    public AuthenticationSuccessHandler appAuthenticationSuccessHandler(){
        return new AppAuthenticationSuccessHandler();
    }
    @Bean //Ставим степень кодировки, с которой кодировали пароль в базе данных
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

//    @Bean
//    UserDetailsManager inMemoryUserDetailsManager() {
////        var user1 = User.withEmail("user").password("{noop}password").roles("USER").build();
//        return new InMemoryUserDetailsManager(user1);
//    }
}
