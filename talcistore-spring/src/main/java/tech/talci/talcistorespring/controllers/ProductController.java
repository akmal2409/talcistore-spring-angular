package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.services.ProductService;

@RestController
@RequestMapping(ProductController.BASE_URL)
@RequiredArgsConstructor
public class ProductController {
    public static final String BASE_URL = "/api/products";
    private final ProductService productService;

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.save(productDto);
    }
}
