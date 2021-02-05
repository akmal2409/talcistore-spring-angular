package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcistorespring.dto.PageResponse;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.services.ProductService;


@RestController
@RequestMapping(ProductController.BASE_URL)
@RequiredArgsConstructor
public class ProductController {
    public static final String BASE_URL = "/api/products";
    private final ProductService productService;

    //TODO: Add UserController for the management purposes

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.save(productDto);
    }

    @GetMapping
    public PageResponse<ProductDto> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size,
            @RequestParam(required = false) boolean sortByPrice,
            @RequestParam(required = false) boolean desc
    ) {
        return productService.getAll(page, size, sortByPrice, desc);
    }

    @GetMapping("/search-product/{nameOrDescription}")
    public PageResponse<ProductDto> searchFor(@PathVariable String nameOrDescription,
                                              @RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "15") Integer size,
                                              @RequestParam(required = false) boolean sortByPrice,
                                              @RequestParam(required = false) boolean desc) {
        return productService.searchByKeyword(nameOrDescription, page, size, sortByPrice, desc);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/by-category/{categoryId}")
    public PageResponse<ProductDto> findAllByCategory(@PathVariable Long categoryId,
                                                      @RequestParam(defaultValue = "0") Integer page,
                                                      @RequestParam(defaultValue = "15") Integer size,
                                                      @RequestParam(required = false) boolean sortByPrice,
                                                      @RequestParam(required = false) boolean desc) {
        return productService.findAllByCategoryId(categoryId, page, size, sortByPrice, desc);
    }

    @GetMapping("/by-seller/{sellerId}")
    public PageResponse<ProductDto> findAllBySeller(@PathVariable Long sellerId,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "15") Integer size,
                                                    @RequestParam(required = false) boolean sortByPrice,
                                                    @RequestParam(required = false) boolean desc) {
        return productService.findAllBySellerId(sellerId, page, size, sortByPrice, desc);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
