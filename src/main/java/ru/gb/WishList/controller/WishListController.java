package ru.gb.WishList.controller;
import lombok.AllArgsConstructor;
import ru.gb.WishList.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import ru.gb.WishList.service.UserService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.java.Log;


@Log
@Controller
@AllArgsConstructor
public class WishListController {

    private final UserService userService;
    @GetMapping("/index")
    public String getStartPage(){
        return "index";
    }



    @GetMapping("/correct_info")
    public String getCorrectInfo(Model model){
        User user = userService.findUserById(1L);
        model.addAttribute("user", user);
        return "correct_info";
    }

    @GetMapping("/personal_office")
    public String getPersonalOffice(Model model){
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
        User user = userService.findUserById(1L);
        model.addAttribute("user", user);
        log.severe("GET personal_office");
        return "personal_office";
    }

    @GetMapping("/registration")
    public String getRegistration(){
        log.severe("GET registration");
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(User user){
        userService.addUser(user);
        return "personal_office";
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
    @GetMapping("/goods")
    public String getGoods(){
        log.severe("Get goods");
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