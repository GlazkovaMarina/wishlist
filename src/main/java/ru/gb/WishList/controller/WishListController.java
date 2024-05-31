package ru.gb.WishList.controller;

import lombok.AllArgsConstructor;
import ru.gb.WishList.domain.Product;
import ru.gb.WishList.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import ru.gb.WishList.service.userService.UserService;
import ru.gb.WishList.service.productService.ProductService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.java.Log;
import java.util.List;




@Log
@Controller
@AllArgsConstructor
public class WishListController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/index")
    public String getStartPage(){
        return "index";
    }


    // Страница "Редактирование личных данных пользователя"
    @GetMapping("/correct_info/{id}")
    public String getCorrectInfo(@PathVariable("id") Long id, Model model){
        // TODO: конкретного пользователя подставлять!
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        log.severe("Get correct_info");
        //String returnPage = "/correct_info/" + id; // формируем персональный личный кабинет
        return "correct_info.html";
    }

    // Редактирование личных данных пользователя в БД
    @PostMapping("/correct_info/{id}")
    public String postCorrectInfo(User user, @PathVariable Long id){
        log.severe("Post correct_info");
        userService.saveUser(user);
        String returnPage = "redirect:/personal_office/" + id; // формируем персональный личный кабинет
        return returnPage;
    }


    @GetMapping("/personal_office/{id}")
    public String getPersonalOffice(Model model, @PathVariable Long id){
        // TODO: убрать готового юзера, считать из бд
//        User user = new User();
//        user.setLastName("Ivanov");
//        user.setFirstName("Ivan");
//        user.setSurname("Ivanovich");
//        user.setNumber(79009090909L);
//        user.setEmail("q@q.ru");
//        String date = "2016-08-16";
//        LocalDate localDate = LocalDate.parse(date);
//        user.setBirthday(localDate);
//        userService.save(user);
        // TODO: конкретного пользователя подставлять!
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        log.severe("GET personal_office");
        return "personal_office.html";
    }

    @GetMapping("/registration")
    public String getRegistration(){
        log.severe("GET registration");
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(User user){
        userService.saveUser(user);
        String returnPage = "redirect:/personal_office/" + user.getId(); // формируем персональный личный кабинет
        return returnPage;
    }
    @GetMapping("/entry")
    public String getEntry(){
        log.severe("Get entry");
        return "entry";
    }
    @GetMapping("/card_item")
    public String getCardItem(){
        log.severe("Get card_item");
        return "card_item";
    }
    @GetMapping("/card_present")
    public String getCardPresent(){
        log.severe("Get card_present");
        return "card_present";
    }
    @GetMapping("/edit_item")
    public String getEditItem(){
        log.severe("Get edit_item");
        return "edit_item";
    }
    @GetMapping("/edit_present")
    public String getEditPresent(){
        log.severe("Get edit_present");
        return "edit_present";
    }
    @GetMapping("/goods/{id}")
    public String getGoods(Model model, @PathVariable Long id){
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        log.severe("Get goods");
        List<Product> products =  productService.findAllProducts();
        model.addAttribute("products", products);
        return "goods";
    }

    @GetMapping("/new_item")
    public String getNewItem(){
        log.severe("Get new_item");
        return "new_item";
    }

    @GetMapping("/wishlist")
    public String getWishlist(){
        log.severe("Get wishlist");
        return "wishlist";
    }

}