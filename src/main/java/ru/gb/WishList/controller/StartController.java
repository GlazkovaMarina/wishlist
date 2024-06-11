package ru.gb.WishList.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.java.Log;

@Tag(name="StartController", description="Контроллер для главной страницы, входа и выхода")
@Log
@Controller
@AllArgsConstructor
public class StartController {
    @GetMapping("/index")
    public String getStartPage(){
        log.severe("Get index");
        return "index";
    }
    @Operation(
            summary = "Страница входа",
            description = "Заполнение данных пользователя для аутентификации пользователя"
    )
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    // @PostMapping("/login") формируется Spring Boot автоматически
    // @PostMapping("/logout") формируется Spring Boot автоматически
}
