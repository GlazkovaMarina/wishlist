package ru.gb.WishList.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Wishlist Api",
                description = "A microservice that " +
                        "allows you to create a list of" +
                        " desired gifts from products presented" +
                        " on the marketplace", version = "1.0.0",
                contact = @Contact(
                        name = "Glazkova Marina",
                        email = "glazkova.mv@bk.ru",
                        url = "https://t.me/@Marina_Glazkova"
                )
        )
)
public class OpenApiConfig {
}
