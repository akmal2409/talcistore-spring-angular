package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.services.ProductService;

import java.util.List;

@RestController
@RequestMapping(ProductController.BASE_URL)
@RequiredArgsConstructor
public class ProductController {
    public static final String BASE_URL = "/api/products";
    private final ProductService productService;

    //TODO: Pagination of products
    //TODO: Add UserController for the management purposes

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.save(productDto);
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
