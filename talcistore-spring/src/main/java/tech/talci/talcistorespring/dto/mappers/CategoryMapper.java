package tech.talci.talcistorespring.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import tech.talci.talcistorespring.dto.CategoryDto;
import tech.talci.talcistorespring.model.Category;
import tech.talci.talcistorespring.repositories.ProductRepository;


@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    @Autowired
    private ProductRepository productRepository;

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Category mapToCategory(CategoryDto categoryDto);

    @Mapping(target = "numberOfProducts", expression = "java(numberOfProducts(category))")
    public abstract CategoryDto mapToCategoryDto(Category category);

    public Integer numberOfProducts(Category category) {
        return Math.toIntExact(productRepository.countByCategory(category));
    }
}
