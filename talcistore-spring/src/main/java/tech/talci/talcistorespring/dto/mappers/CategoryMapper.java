package tech.talci.talcistorespring.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.talci.talcistorespring.dto.CategoryDto;
import tech.talci.talcistorespring.model.Category;
import tech.talci.talcistorespring.model.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "numberOfProducts", ignore = true)
    @Mapping(target = "id", ignore = true)
    Category mapToCategory(CategoryDto categoryDto);

    @Mapping(target = "numberOfProducts", expression = "java(getNumberOfProducts(category.getProducts()))")
    CategoryDto mapToCategoryDto(Category category);

    default Integer getNumberOfProducts(List<Product> products) {
        return products.size();
    }
}
