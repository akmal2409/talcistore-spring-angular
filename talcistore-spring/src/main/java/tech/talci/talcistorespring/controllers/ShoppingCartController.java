package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.dto.ShoppingCartDto;
import tech.talci.talcistorespring.services.ShoppingCartService;

import java.util.List;

@RestController
@RequestMapping(ShoppingCartController.BASE_URL)
@RequiredArgsConstructor
public class ShoppingCartController {

    public static final String BASE_URL = "/api/cart";
    private final ShoppingCartService cartService;

    @GetMapping
    public List<ProductDto> getAllFromCart() {
        return cartService.getAllProducts();
    }

    @GetMapping("/last-five")
    public ShoppingCartDto getLastFiveItems() {
        return cartService.getCartLastFive();
    }
}
