package ru.gb.WishList.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import lombok.AllArgsConstructor;

import java.security.Principal;
import java.util.Collections;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import ru.gb.WishList.entities.Product;
import ru.gb.WishList.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import ru.gb.WishList.service.userService.UserService;
import ru.gb.WishList.service.productService.ProductService;
import ru.gb.WishList.service.giftService.GiftService;
import lombok.extern.java.Log;
import java.util.List;
import ru.gb.WishList.entities.Gift;
import ru.gb.WishList.exception.UserAlreadyExistException;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.gb.WishList.entities.enums.Role;

@Tag(name="WishlistController", description="Контроллер для списка подарков")
@Log
@Controller
@AllArgsConstructor
public class WishlistController {

    private final UserService userService;
    private final ProductService productService;
    private final GiftService giftService;

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


    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/card_present/{user_id}/{gift_id}")
    public String getCardPresent(@PathVariable("user_id") Long userId, @PathVariable("gift_id") Long giftId, Model model){
        model.addAttribute("user_id", userId);
        Gift gift = giftService.findGiftById(giftId);
        model.addAttribute("gift", gift);
        log.severe("Get card_present");
        return "card_present";
    }


    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/edit_present/{user_id}/{gift_id}")
    public String getEditPresent(@PathVariable("user_id") Long userId, @PathVariable("gift_id") Long giftId, Model model){
        model.addAttribute("user_id", userId);
        Gift gift = giftService.findGiftById(giftId);
        model.addAttribute("gift", gift);
        model.addAttribute("product_id", gift.getProduct().getId());
        log.severe("Get edit_present");
        return "edit_present";
    }
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/edit_present/{user_id}/{product_id}/{gift_id}")
    public String postEditPresent(@PathVariable("user_id") Long userId, @PathVariable("product_id") Long productId, @PathVariable("gift_id") Long giftId, Gift gift){
        log.severe("Post edit_present");
        gift.setProduct(productService.findProductById(productId));
        giftService.saveGift(gift);
        String returnPage = "redirect:/card_present/" + userId + "/" + giftId;
        return returnPage;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/wishlist/{user_id}")
    public String getWishlist(@PathVariable("user_id") Long userId, Model model){
        model.addAttribute("user_id", userId);
        List<Gift> gifts =  giftService.findAllGifts();
        model.addAttribute("gifts", gifts);
        log.severe("Get wishlist");
        return "wishlist";
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/new_present/{user_id}/{item_id}")
    public String getNewPresent(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        model.addAttribute("gift", new Gift());
        log.severe("Get new_present");
        return "new_present";
    }
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/new_present/{user_id}/{item_id}")
    public String getNewPresent(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId,  Gift gift){
        User user = userService.findUserById(userId);
        Product product = productService.findProductById(itemId);
        gift.setOwner(user);
        gift.setProduct(product);
        log.severe("Post new_present");
        giftService.saveGift(gift);
        String returnPage = "redirect:/wishlist/" + userId;
        return returnPage;
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
    // Редактирование личных данных пользователя в БД
    @PostMapping("/correct_info/{user_id}")
    public String postCorrectInfo(User user, @PathVariable("user_id") Long userId){
        User originUser = userService.findUserById(userId);
        user.setPassword(originUser.getPassword());
        user.setRoles(originUser.getRoles());
        userService.updateUser(user);
        String returnPage = "redirect:/personal_office"; // формируем персональный личный кабинет
        return returnPage;
    }


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
    @GetMapping("/card_item/{user_id}/{item_id}")
    public String getCardItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        log.severe("Get card_item");
        return "card_item";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit_item/{user_id}/{item_id}")
    public String getEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Model model){
        model.addAttribute("user_id", userId);
        Product product = productService.findProductById(itemId);
        model.addAttribute("product", product);
        log.severe("Get edit_item");
        return "edit_item";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit_item/{user_id}/{item_id}")
    public String postEditItem(@PathVariable("user_id") Long userId, @PathVariable("item_id") Long itemId, Product product){
        productService.saveProduct(product);
        String returnPage = "redirect:/card_item/" + userId + "/" + itemId; // формируем персональный личный кабинет
        log.severe("Post edit_item");
        return returnPage;
    }

    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @GetMapping("/goods/{user_id}")
    public String getGoods(Model model, @PathVariable("user_id") Long userId){
        model.addAttribute("user_id", userId);
        log.severe("Get goods");
        List<Product> products =  productService.findAllProducts();
        model.addAttribute("products", products);
        return "goods";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new_item/{user_id}")
    public String getNewItem(@PathVariable("user_id") Long userId, Model model){
        log.severe("Get new_item");
        model.addAttribute("user_id", userId);
        return "new_item";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new_item/{user_id}")
    public String postNewItem(@PathVariable("user_id") Long userId, Product product){
        log.severe("Post new_item");
        productService.saveProduct(product);
        String returnPage = "redirect:/goods/" + userId;
        return returnPage;
    }

}
