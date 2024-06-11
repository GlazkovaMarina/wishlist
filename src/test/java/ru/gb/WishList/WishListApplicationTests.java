package ru.gb.WishList;
import io.swagger.v3.oas.annotations.Operation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class WishListApplicationTests {

	@Test
	@Operation(
			summary = "Тестирование работоспособности contextLoads()",
			description = "Проверка работоспособности, которая завершится неудачей, если контекст приложения не запустится"
	)
	void contextLoads() {
	}

}
