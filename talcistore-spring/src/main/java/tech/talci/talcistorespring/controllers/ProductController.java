package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.model.Product;
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
    public Slice<ProductDto> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size,
            @RequestParam(required = false) boolean sortByPrice,
            @RequestParam(required = false) boolean desc
    ) {
        return productService.getAll(page, size, sortByPrice, desc);
    }

    @GetMapping("/search-product/{nameOrDescription}")
    public Slice<ProductDto> searchFor(@PathVariable String nameOrDescription) {
        return productService.searchByKeyword(nameOrDescription);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
