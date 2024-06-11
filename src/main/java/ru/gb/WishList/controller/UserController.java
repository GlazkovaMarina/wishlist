package ru.gb.WishList.controller;

import java.security.Principal;
import java.util.Collections;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.gb.WishList.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import ru.gb.WishList.service.userService.UserService;
import lombok.extern.java.Log;
import ru.gb.WishList.exception.UserAlreadyExistException;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.gb.WishList.entities.enums.Role;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@Tag(name="UserController", description="Контроллер для пользователя")
@Log
@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

     @Operation(
            summary = "Страница регистрации",
            description = "Заполнение данных пользователя для регистрации нового пользователя"
    )
    @GetMapping("/registration")
    public String register (Model model){
        User user = new User(); // Создаем нового пользователя и передаем на страницу, чтобы потом передавать считанные данные не черз отдельные переменные
        user.setRoles(Collections.singleton(Role.USER)); // Зарегистрироваться можно только для роли USER
        model.addAttribute("user", user); // Добавляем объект на страницу с идентификатором user
        model.addAttribute("error", ""); // TODO: переделать на более популярный вариант обработки ошибок
        return "registration";
    }
    @Operation(
            summary = "Страница регистрации",
            description = "ДЛобавление нового пользователя в базу данных"
    )
    @PostMapping("/registration")
    public ModelAndView registerUserAccount(User user, Model model) {
        try {
            userService.register(user); // сохраняем нового пользователя в базе данных
        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("registration", "user", user);
            model.addAttribute("error", "Аккаунт c таким username/email уже существует. Нажмите _Вход_");
            return mav;
        }
        return new ModelAndView("redirect:/login");
    }

    // TODO: добавить из этого примера валидацию
    //@PostMapping("/registration")
    //    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
    //
    //        if (bindingResult.hasErrors()) {
    //            return "registration";
    //        }
    //        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
    //            model.addAttribute("passwordError", "Пароли не совпадают");
    //            return "registration";
    //        }
    //        if (!userService.saveUser(userForm)){
    //            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
    //            return "registration";
    //        }
    //
    //        return "redirect:/";
    //    }

    @Operation(
            summary = "Личный кабинет пользователя",
            description = "Позволяет пользователю просмотреть свои личные данные, перейти на страницу 'Редактирование данных', 'Список подарков'. Для администратора доступно 'Добавление нового товара'"
    )
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @GetMapping("/personal_office")
    public String getPersonalOffice(Model model, Principal principal){
        User currentUser = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", currentUser);
        return "personal_office";
    }

    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    // Страница "Редактирование личных данных пользователя"
    @GetMapping("/correct_info/{user_id}")
    public String getCorrectInfo(@PathVariable("user_id") Long userId, Model model){
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "correct_info";
    }

    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @PostMapping("/correct_info/{user_id}")
    public String postCorrectInfo(User user, @PathVariable("user_id") Long userId){
        User originUser = userService.findUserById(userId);
        user.setPassword(originUser.getPassword());
        user.setRoles(originUser.getRoles());
        userService.updateUser(user);
        String returnPage = "redirect:/personal_office"; // формируем персональный личный кабинет
        return returnPage;
    }

}
