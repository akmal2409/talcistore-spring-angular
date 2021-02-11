package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcistorespring.dto.PageResponse;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.dto.SearchResultOptions;
import tech.talci.talcistorespring.services.ProductService;

import java.util.List;


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

    @GetMapping
    public PageResponse<ProductDto> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size,
            @RequestParam(required = false) boolean sortByPrice,
            @RequestParam(required = false) boolean desc
    ) {
        return productService.getAll(page, size, sortByPrice, desc);
    }

    @GetMapping("/top-rated")
    public PageResponse<ProductDto> getAllSortedByRating(@RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "20") Integer size) {
        return productService.getAllOrderedByRating(page, size);
    }

    @GetMapping("/search-product")
    public PageResponse<ProductDto> searchFor(@RequestParam String text,
                                              @RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "15") Integer size,
                                              @RequestParam(required = false) boolean sortByPrice,
                                              @RequestParam(required = false) boolean desc) {
        return productService.searchByKeyword(text, page, size, sortByPrice, desc);
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
