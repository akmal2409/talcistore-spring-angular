package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcistorespring.dto.CategoryDto;
import tech.talci.talcistorespring.model.Category;
import tech.talci.talcistorespring.services.CategoryService;

import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;

@RestController
@RequestMapping(CategoryController.BASE_URL)
@RequiredArgsConstructor
public class CategoryController {

    public static final String BASE_URL = "/api/categories";
    private final CategoryService categoryService;

    @PostMapping
    public void createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.save(categoryDto);
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/check-name/{name}")
    public Map<String, Boolean> checkAvailabilityOfCategoryName(@PathVariable String name) {
        return singletonMap("available", categoryService.isCategoryNameAvailable(name));
    }
}
