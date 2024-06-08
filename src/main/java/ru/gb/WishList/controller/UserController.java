//package ru.gb.WishList.controller;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import ru.gb.WishList.entities.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.ui.Model;
//import ru.gb.WishList.service.userService.UserService;
//import lombok.extern.java.Log;
//import lombok.AllArgsConstructor;
//@Log
//@Controller
//@AllArgsConstructor
//public class UserController {
//    private final UserService userService;
//
////    @PreAuthorize("hasAuthority('USER')")
//    // Страница "Редактирование личных данных пользователя"
//    @GetMapping("/correct_info/{id}")
//    public String getCorrectInfo(@PathVariable("id") Long id, Model model){
//        User user = userService.findUserById(id);
//        model.addAttribute("user", user);
//        log.severe("Get correct_info");
//        //String returnPage = "/correct_info/" + id; // формируем персональный личный кабинет
//        return "correct_info.html";
//    }
//
////    @PreAuthorize("hasAuthority('USER')")
//    // Редактирование личных данных пользователя в БД
//    @PostMapping("/correct_info/{id}")
//    public String postCorrectInfo(User user, @PathVariable Long id){
//        User originUser = userService.findUserById(id);
//        log.severe("Post correct_info");
//        user.setId(id);
//        user.setPassword(originUser.getPassword());
//        userService.updateUser(user);
//        String returnPage = "redirect:/personal_office/" + id; // формируем персональный личный кабинет
//        return returnPage;
//    }
//
////    @PreAuthorize("hasAuthority('USER')")
//    @GetMapping("/personal_office/{user_id}")
//    public String getPersonalOffice(Model model, @PathVariable("user_id") Long userId){
//        // TODO: убрать готового юзера, считать из бд
////        User user = new User();
////        user.setLastName("Ivanov");
////        user.setFirstName("Ivan");
////        user.setSurname("Ivanovich");
////        user.setNumber(79009090909L);
////        user.setEmail("q@q.ru");
////        String date = "2016-08-16";
////        LocalDate localDate = LocalDate.parse(date);
////        user.setBirthday(localDate);
////        userService.save(user);
//        User user = userService.findUserById(userId);
//        model.addAttribute("user", user);
//        log.severe("GET personal_office");
//        return "personal_office.html";
//    }
//
////    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/personal_office_admin/{user_id}")
//    public String getPersonalOfficeAdmin(Model model, @PathVariable("user_id") Long userId){
//        User user = userService.findUserById(userId);
//        model.addAttribute("user", user);
//        log.severe("GET personal_office");
//        return "personal_office_admin.html";
//    }
//
//
//}
