package tech.talci.talcistorespring.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.model.Category;
import tech.talci.talcistorespring.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "orderCount", ignore = true)
    @Mapping(target = "addedOn", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    Product mapToProduct(ProductDto productDto, Category category);

    @Mapping(target = "categoryId", source = "category.id")
    ProductDto mapToProductDto(Product product);

}
