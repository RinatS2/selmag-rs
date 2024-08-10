package com.suleimanov.manager.controller;

import com.suleimanov.manager.client.BadRequestException;
import com.suleimanov.manager.client.ProductsRestClient;
import com.suleimanov.manager.controller.payload.NewProductPayload;
import com.suleimanov.manager.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor //Создаст конструктор, который содержит арг-ты для final св-в
@RequestMapping("catalogue/products") //мапим на уровня класса, указываем путь
public class ProductsController { //Контроллер, который обрабатывает http запросы

    private final ProductsRestClient productsRestClient;

    @GetMapping("list") // @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productsRestClient.findAllProducts());

        return "catalogue/products/list"; //вернём шаблон страницы
    }

    @GetMapping("create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload,
                                Model model) {
        try {
            Product product = this.productsRestClient.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "catalogue/products/new_product";
        }
    }
}
