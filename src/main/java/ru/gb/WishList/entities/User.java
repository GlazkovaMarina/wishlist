package ru.gb.WishList.entities;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.gb.WishList.entities.enums.Role;
//import ru.gb.WishList.validation.PasswordMatches;
//import ru.gb.WishList.validation.ValidEmail;

import java.time.LocalDate;

/**
 * User - пользователь
 * id - идентификационный номер пользователя в базе данных
 * lastName - фамилия
 * firstName - имя
 * surname - отчество
 * birthday - день, месяц и год рождения
 * number - номер телефона
 * email - электронная почта
 * wishlist - список желаемых подарков
 * Data   -  дает нам геттеры для всех полей, сеттеры для всех нефинальных полей, правильные реализации toString,
 *            equals и hashCode, охватывающие все поля класса,
 *            а также конструктор для всех финальных полей
 * Entity -  представляет собой таблицу, хранящуюся в базе данных. Каждый экземпляр сущности представляет
 *            строку в таблице
 * Table  -  задание имени таблицы в базе данных
 * Id     -  определяет первичный ключ. Мы можем генерировать идентификаторы разными способами,
 *            которые указаны в аннотации @GeneratedValue
 * Column -  при необходимости задаем имя поля в таблице, возможность хранения null и уникальность
 **/
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
//    @NotEmpty(message = "Поле _фамилия_ не может быть пустым")
    @Column (name = "last_name", nullable = false)
    private String lastName;

//    @NotEmpty(message = "Поле _имя_ не может быть пустым")
    @Column (name = "first_name", nullable = false)
    private String firstName;

    private String surname;
//    @NotEmpty(message = "Поле _день рождения_ не может быть пустым")
    @Column (nullable = false)
    private LocalDate birthday;

    // TODO: если разберусь с безопасностью, то реализовать аутентификацию и с помощью телефона
////    @NotEmpty(message = "Поле _номер телефона_ не может быть пустым")
//    @Column (nullable = false, unique = true)
//    private Long number;

//    @ValidEmail
//    @NotEmpty(message = "Поле _электронная почта_ не может быть пустым")
    @Column (nullable = false, unique = true)
    private String username;
    //private String email;
//    @NotEmpty(message = "Поле _пароль_ не может быть пустым")
    @Column(nullable=false)
    private String password;
    // TODO: если успею, то вернуть корректную проверку пароля
//    @Column (name = "matching_password",nullable=false)
//    private String matchingPassword;

    @OneToMany(mappedBy="owner")
    private Set<Gift> gifts;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;

    // TODO: удалить лишние реализации role
    //    @Column (nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Role role;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Role> roles;

//    @ManyToMany
//    @JoinTable(
//        name = "users_roles",
//        joinColumns = @JoinColumn(
//                name = "user_id", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(
//                name = "role_id", referencedColumnName = "id"))
//    private Set<Role> roles;

//Список ролей связан с пользователем отношением многие ко многим
    // (один пользователь может иметь несколько ролей с одной стороны
    // и у одной роли может быть несколько пользователей с другой);
    // FetchType.EAGER – «жадная» загрузка, т.е. список ролей загружается
    // вместе с пользователем сразу (не ждет пока к нему обратятся).


//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;
//
////    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
////    @JoinTable(
////            name="users_roles",
////            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
////            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
////    private List<Role> roles = new ArrayList<>();



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
