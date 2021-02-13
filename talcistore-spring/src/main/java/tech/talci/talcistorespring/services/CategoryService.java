package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.talcistorespring.dto.CategoryDto;
import tech.talci.talcistorespring.dto.mappers.CategoryMapper;
import tech.talci.talcistorespring.exceptions.CategoryManipulationException;
import tech.talci.talcistorespring.exceptions.ResourceNotFoundException;
import tech.talci.talcistorespring.model.Category;
import tech.talci.talcistorespring.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category was not found ID: " + id));

        return categoryMapper.mapToCategoryDto(category);
    }
}
