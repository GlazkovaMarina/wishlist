//package ru.gb.WishList.controller;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.extern.java.Log;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import ru.gb.WishList.entities.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.servlet.ModelAndView;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.validation.Errors;
//import jakarta.validation.Valid;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.validation.BindingResult;
//import org.springframework.ui.Model;
//import ru.gb.WishList.entities.Role;
//import java.util.Collections;
//import ru.gb.WishList.service.userService.UserService;
//import ru.gb.WishList.exception.UserAlreadyExistException;
//
//@Log
//@Controller
//@AllArgsConstructor
//@NoArgsConstructor
//public class RegistrationController {
//
//    private UserService userService;
////    @GetMapping("/registration")
////    public String registration() {
////        return "registration";
////    }
//
//    @GetMapping("/registration")
//    public String registration (Model model){
//        User user = new User();
//        model.addAttribute("user", user);
//        model.addAttribute("error", "");
//        log.severe("GET registration");
//        return "registration";
//    }
//
//
//    @PostMapping("/registration")
//    public ModelAndView registerUserAccount(User user, Model model) {
//
//        log.severe(user.toString());
//        try {
//            log.severe("Post registration_DO");
//            userService.registrate(user);
//            log.severe("Post registration");
//        } catch (UserAlreadyExistException uaeEx) {
//            ModelAndView mav = new ModelAndView("registration", "user", user);
//            model.addAttribute("error", "Аккаунт c таким username/email уже существует. Нажмите _Вход_");
////            mav.addObject("error", "Аккаунт для этого username/email уже существует.");
//            log.severe("Post registration_catch");
////            String errMessage = messages.getMessage("message.regError", null, request.getLocale());
////            mav.addObject("message", errMessage);
//            return mav;
//        }
//        String returnPage = "redirect:/login.html"; // формируем персональный личный кабинет
//        return new ModelAndView(returnPage, "user", user);
//    }
//}
