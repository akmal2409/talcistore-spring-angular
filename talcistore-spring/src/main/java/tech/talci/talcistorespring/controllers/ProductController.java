package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcistorespring.dto.PageResponse;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.dto.SearchResultOptions;
import tech.talci.talcistorespring.services.ProductService;

@RestController
@RequestMapping(ProductController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    public static final String BASE_URL = "/api/products";
    private final ProductService productService;

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.save(productDto);
    }

    @GetMapping
    public PageResponse<ProductDto> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size,
            @RequestParam(required = false) MultiValueMap<String, String> filters
            ) {
        return productService.getAll(page, size, filters);
    }

    @GetMapping("/search-product")
    public PageResponse<ProductDto> searchFor(@RequestParam String text,
                                              @RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "15") Integer size,
                                              @RequestParam(required = false) MultiValueMap<String, String> filters) {
        return productService.searchByKeyword(text, page, size, filters);
    }

    @GetMapping("/get-search-options")
    public SearchResultOptions getSearchResultOptions(@RequestParam String text) {
        return productService.getSearchResultOptions(text);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/by-category/{categoryId}")
    public PageResponse<ProductDto> findAllByCategory(@PathVariable Long categoryId,
                                                      @RequestParam(defaultValue = "0") Integer page,
                                                      @RequestParam(defaultValue = "15") Integer size,
                                                      @RequestParam(required = false) MultiValueMap<String, String> filters) {
        return productService.findAllByCategoryId(categoryId, page, size, filters);
    }

    @GetMapping("/by-seller/{sellerId}")
    public PageResponse<ProductDto> findAllBySeller(@PathVariable Long sellerId,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "15") Integer size,
                                                    @RequestParam(required = false) MultiValueMap<String, String> filters) {
        return productService.findAllBySellerId(sellerId, page, size, filters);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
