package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.talcistorespring.dto.CategoryDto;
import tech.talci.talcistorespring.dto.mappers.CategoryMapper;
import tech.talci.talcistorespring.exceptions.CategoryManipulationException;
import tech.talci.talcistorespring.model.Category;
import tech.talci.talcistorespring.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public void save(CategoryDto categoryDto) {
        if (!isCategoryNameAvailable(categoryDto.getCategoryName())) {
            throw new CategoryManipulationException("Category with " + categoryDto.getCategoryName() + " already " +
                    "exists");
        }

        Category category = categoryMapper.mapToCategory(categoryDto);

        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Boolean isCategoryNameAvailable(String name) {
        return categoryRepository.findCategoryByCategoryName(name)
                .isEmpty();
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());
    }
}
