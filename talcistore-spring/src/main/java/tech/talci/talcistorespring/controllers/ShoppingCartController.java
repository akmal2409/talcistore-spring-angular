package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcistorespring.dto.ModifyCartRequest;
import tech.talci.talcistorespring.services.ShoppingCartService;

import javax.validation.Valid;
@RestController
@RequestMapping(ShoppingCartController.BASE_URL)
@RequiredArgsConstructor
public class ShoppingCartController {

    public static final String BASE_URL = "/api/cart";
    private final ShoppingCartService cartService;

    @PostMapping
    public void modifyCart(@RequestBody @Valid ModifyCartRequest cartRequest) {
        cartService.modifyCart(cartRequest);
    }
}
